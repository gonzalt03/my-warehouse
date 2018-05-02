package fr.onefox.mywarehouse.services;

import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.repositories.TransactionRepositoryService;
import fr.onefox.mywarehouse.view.export.CargoMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static fr.onefox.mywarehouse.domain.TransactionType.WAREHOUSE_MOVEMENT_IN;
import static fr.onefox.mywarehouse.domain.TransactionType.WAREHOUSE_MOVEMENT_OUT;
import static org.apache.commons.lang3.StringUtils.isEmpty;


@Service
@Slf4j
public class TransactionService {

    private static final String AWB = "AWB";
    private static final String AWB_CONSTRAINTS = "AWB's constraints are not respected";
    private static final String QUANTITY_AND_TOTAL_WEIGHT_CONSTRAINTS = "The quantity and total weight of the reference must each be greater than or equal to the quantity and weight of the goods of the movement.";
    private static final String DECLARE_AN_EXIT_CONSTRAINTS = "We can not declare an exit on a merchandise reference whose entry we have not declared";
    private static final String EMPTY_CUSTOMS_DOC_CONSTRAINTS = "Missing the exit document";
    private static final int AWB_CONDITION_REFCODE_LENGTH = 11;

    @Autowired
    private TransactionRepositoryService transactionRepositoryService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExportXmlService exportXmlService;

    /**
     * Add transaction
     *
     * @param transaction
     * @return
     * @throws Exception
     */
    public Transaction insertTransaction(Transaction transaction) throws Exception {
        // Verification of business constraints
        if (AWB.equals(transaction.getRefType())
                && (transaction.getRefCode().length() != AWB_CONDITION_REFCODE_LENGTH || !StringUtils.isNumeric(transaction.getRefCode()))) {
            log.error(AWB_CONSTRAINTS);
            throw new Exception(AWB_CONSTRAINTS);
        }

        if (transaction.getAmountQuantity() > transaction.getTotalRefAmountQuantity()
                && transaction.getAmountWeight() > transaction.getTotalRefAmountWeight()) {
            log.error(QUANTITY_AND_TOTAL_WEIGHT_CONSTRAINTS);
            throw new Exception(QUANTITY_AND_TOTAL_WEIGHT_CONSTRAINTS);
        }

        if (transaction.getType() == WAREHOUSE_MOVEMENT_OUT) {
            if (isEmpty(transaction.getCustomsDocRef()) || isEmpty(transaction.getCustomsDocType())) {
                log.error(EMPTY_CUSTOMS_DOC_CONSTRAINTS);
                throw new Exception(EMPTY_CUSTOMS_DOC_CONSTRAINTS);
            }

            // If it is an exit, check that there is an entry
            Long out = transactionRepositoryService.countByRefCodeAndType(transaction.getRefCode(), WAREHOUSE_MOVEMENT_OUT);
            Long in = transactionRepositoryService.countByRefCodeAndType(transaction.getRefCode(), WAREHOUSE_MOVEMENT_IN);

            if (in == 0 || in < out + 1) {
                log.error(DECLARE_AN_EXIT_CONSTRAINTS);
                throw new Exception(DECLARE_AN_EXIT_CONSTRAINTS);
            }
        }

        transaction = transactionRepositoryService.saveOrUpdate(transaction);

        // Send Email & Exportation XML
        emailService.sendTransactionEmail(transaction, exportXmlService.export("export_" + String.valueOf(transaction.get_id()), new CargoMessage(transaction)));

        return transaction;
    }

}
