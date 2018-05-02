package fr.onefox.mywarehouse.services;

import fr.onefox.mywarehouse.UtilsCreator;
import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.domain.TransactionType;
import fr.onefox.mywarehouse.repositories.TransactionRepositoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static fr.onefox.mywarehouse.domain.TransactionType.WAREHOUSE_MOVEMENT_IN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    private Transaction transaction;

    // Use with @InjectMocks
    @Mock
    EmailService emailService;

    // Use with @InjectMocks
    @Mock
    ExportXmlService exportXmlService;

    @Mock
    TransactionRepositoryService transactionRepositoryService;

    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void setUp() {
        transaction = UtilsCreator.createTransaction();
        when(transactionRepositoryService.saveOrUpdate(any())).thenReturn(transaction);
    }

    @Test(expected = Exception.class)
    public void insertTransactionAWBLength() throws Exception {
        transaction.setRefType("AWB");
        transaction.setRefCode("111");
        transactionService.insertTransaction(transaction);
    }

    @Test(expected = Exception.class)
    public void insertTransactionAWBNumber() throws Exception {
        transaction.setRefType("AWB");
        transaction.setRefCode("1131333333A");
        transactionService.insertTransaction(transaction);
    }

    @Test
    public void insertTransactionAWBOK() throws Exception {
        transaction.setRefType("AWB");
        transaction.setRefCode("11313333331");
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_IN);
        transactionService.insertTransaction(transaction);
    }

    @Test(expected = Exception.class)
    public void insertTransactionQuantity() throws Exception {
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_OUT);
        transaction.setCustomsDocRef("1");
        transaction.setCustomsDocType("1");
        transaction.setAmountQuantity(2D);
        transaction.setAmountWeight(2D);
        transaction.setTotalRefAmountQuantity(1D);
        transaction.setTotalRefAmountWeight(1D);
        transactionService.insertTransaction(transaction);
    }

    @Test
    public void insertTransactionQuantityOk() throws Exception {
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_IN);
        transaction.setCustomsDocRef("1");
        transaction.setCustomsDocType("1");
        transaction.setAmountQuantity(1D);
        transaction.setAmountWeight(1D);
        transaction.setTotalRefAmountQuantity(2D);
        transaction.setTotalRefAmountWeight(2D);
        transactionService.insertTransaction(transaction);
    }

    @Test(expected = Exception.class)
    public void insertTransactionMissingDocs() throws Exception {
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_OUT);
        transaction.setCustomsDocRef("");
        transaction.setCustomsDocType("");
        transactionService.insertTransaction(transaction);
    }

    @Test
    public void insertTransactionMissingDocsOK() throws Exception {
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_IN);
        transactionService.insertTransaction(transaction);
        Transaction transaction2 = UtilsCreator.createTransaction();
        when(transactionRepositoryService.countByRefCodeAndType(transaction2.getRefCode(), WAREHOUSE_MOVEMENT_IN)).thenReturn(1L);
        transaction2.setType(TransactionType.WAREHOUSE_MOVEMENT_OUT);
        transaction2.setCustomsDocRef("1");
        transaction2.setCustomsDocType("1");
        transactionService.insertTransaction(transaction2);
    }

    @Test(expected = Exception.class)
    public void insertTransactionInOutError() throws Exception {
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_OUT);
        transaction.setCustomsDocRef("1");
        transaction.setCustomsDocType("1");
        transactionService.insertTransaction(transaction);
    }

}