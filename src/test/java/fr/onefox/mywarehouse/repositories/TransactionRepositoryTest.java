package fr.onefox.mywarehouse.repositories;

import fr.onefox.mywarehouse.UtilsCreator;
import fr.onefox.mywarehouse.domain.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static fr.onefox.mywarehouse.UtilsCreator.*;
import static fr.onefox.mywarehouse.domain.TransactionType.WAREHOUSE_MOVEMENT_IN;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;


    @Test
    public void testPersistence() {
        //given
        Transaction transaction = UtilsCreator.createTransaction();

        //when
        transactionRepository.save(transaction);

        //then
        Assert.assertNotNull(transaction.get_id());
        Transaction transactionCreated = transactionRepository.findById(transaction.get_id()).orElse(null);
        assertEquals((Long) 1L, transactionCreated.get_id());
        assertEquals(WAREHOUSE_MOVEMENT_IN, transactionCreated.getType());
        assertEquals(MOVEMENT_TIME, transactionCreated.getMovementTime());
        assertEquals(DECLARED_IN_CODE, transactionCreated.getDeclaredInCode());
        assertEquals(DECLARED_IN_LABEL, transactionCreated.getDeclaredInLabel());
        assertEquals(TO_CODE, transactionCreated.getToCode());
        assertEquals(TO_LABEL, transactionCreated.getToLabel());
        assertEquals(REF_TYPE, transactionCreated.getRefType());
        assertEquals(REF_CODE, transactionCreated.getRefCode());
        assertEquals(AMOUNT_QUANTITY, transactionCreated.getAmountQuantity());
        assertEquals(AMOUNT_WEIGHT, transactionCreated.getAmountWeight());
        assertEquals(DESCRIPTION, transactionCreated.getDescription());
        assertEquals(TOTAL_REF_QUANTITY, transactionCreated.getTotalRefAmountQuantity());
        assertEquals(TOTAL_REF_WEIGHT, transactionCreated.getTotalRefAmountWeight());
        assertEquals(CUSTOMS_STATUS, transactionCreated.getCustomsStatus());
        assertEquals(CUSTOMS_DOC_TYPE, transactionCreated.getCustomsDocType());
        assertEquals(CUSTOMS_DOC_REF, transactionCreated.getCustomsDocRef());
        assertEquals(USER, transactionCreated.getUserInformations());
        assertEquals(MESSAGE_TIME, transactionCreated.getMessageTime());
    }

}