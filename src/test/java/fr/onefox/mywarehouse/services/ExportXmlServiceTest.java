package fr.onefox.mywarehouse.services;

import fr.onefox.mywarehouse.UtilsCreator;
import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.domain.TransactionType;
import fr.onefox.mywarehouse.view.export.CargoMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static fr.onefox.mywarehouse.UtilsCreator.CONTENT_XML_MODEL_MOVEMENT_IN;
import static fr.onefox.mywarehouse.UtilsCreator.CONTENT_XML_MODEL_MOVEMENT_OUT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExportXmlServiceTest {

    private Transaction transaction;

    @Autowired
    private ExportXmlService exportXmlService;

    @Before
    public void setUp() throws Exception {
        transaction = UtilsCreator.createTransaction();
    }

    @Test
    public void exportMovementIn() throws Exception {
        File file = exportXmlService.export("test", new CargoMessage(transaction));

        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String content = new String(encoded, StandardCharsets.UTF_8);

        Assert.assertEquals(content, CONTENT_XML_MODEL_MOVEMENT_IN);
    }

    @Test
    public void exportMovementOut() throws Exception {
        transaction.setType(TransactionType.WAREHOUSE_MOVEMENT_OUT);
        File file = exportXmlService.export("test", new CargoMessage(transaction));

        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String content = new String(encoded, StandardCharsets.UTF_8);

        Assert.assertEquals(content, CONTENT_XML_MODEL_MOVEMENT_OUT);
    }
}