package fr.onefox.mywarehouse;

import fr.onefox.mywarehouse.domain.Transaction;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static fr.onefox.mywarehouse.domain.TransactionType.WAREHOUSE_MOVEMENT_IN;

public class UtilsCreator {

    public static final LocalDateTime MOVEMENT_TIME = LocalDateTime.now();
    public static final String DECLARED_IN_CODE = "CDGRC1";
    public static final String DECLARED_IN_LABEL = "RapidCargo CDG";
    public static final String TO_CODE = "CDGAF1";
    public static final String TO_LABEL = "Air Cargo CDG 1";
    public static final String REF_TYPE = "AWB";
    public static final String REF_CODE = "07712345678";
    public static final Double AMOUNT_QUANTITY = 12D;
    public static final Double AMOUNT_WEIGHT = 345D;
    public static final String DESCRIPTION = "ELECTRONICS";
    public static final Double TOTAL_REF_QUANTITY = 12D;
    public static final Double TOTAL_REF_WEIGHT = 345D;
    public static final char CUSTOMS_STATUS = 'X';
    public static final String CUSTOMS_DOC_TYPE = "T1";
    public static final String CUSTOMS_DOC_REF = "098765432";
    public static final String USER = "Thomas";
    public static final LocalDateTime MESSAGE_TIME = LocalDateTime.now();
    public static ZoneId defaultZoneId = ZoneId.of("Europe/Paris");
    public static final String CONTENT_XML_MODEL_MOVEMENT_IN =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<cargoMessage type=\"WarehouseMovement-In\">\n" +
                    "    <Header from=\"RAPIDCARGO\" to=\"CARGOINFO\" messageTime=\"" + MESSAGE_TIME.atZone(defaultZoneId).toInstant().toString() + "\" messageId=\"null\"/>\n" +
                    "    <WarehouseMovementIn>\n" +
                    "        <movementTime>" + MOVEMENT_TIME.atZone(defaultZoneId).toInstant().toString() + "</movementTime>\n" +
                    "        <declaredIn code=\"CDGRC1\" label=\"RapidCargo CDG\"/>\n" +
                    "        <to code=\"CDGAF1\" label=\"Air Cargo CDG 1\"/>\n" +
                    "        <goods>\n" +
                    "            <ref type=\"AWB\" code=\"07712345678\"/>\n" +
                    "            <amount quantity=\"12.0\" weight=\"345.0\"/>\n" +
                    "            <description>ELECTRONICS</description>\n" +
                    "            <totalRefAmount quantity=\"12.0\" weight=\"345.0\"/>\n" +
                    "        </goods>\n" +
                    "        <customsStatus>X</customsStatus>\n" +
                    "    </WarehouseMovementIn>\n" +
                    "</cargoMessage>\n";

    public static final String CONTENT_XML_MODEL_MOVEMENT_OUT =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<cargoMessage type=\"WarehouseMovement-Out\">\n" +
                    "    <Header from=\"RAPIDCARGO\" to=\"CARGOINFO\" messageTime=\"" + MESSAGE_TIME.atZone(defaultZoneId).toInstant().toString() + "\" messageId=\"null\"/>\n" +
                    "    <WarehouseMovementOut>\n" +
                    "        <movementTime>" + MOVEMENT_TIME.atZone(defaultZoneId).toInstant().toString() +
                    "</movementTime>\n" +
                    "        <declaredIn code=\"CDGRC1\" label=\"RapidCargo CDG\"/>\n" +
                    "        <to code=\"CDGAF1\" label=\"Air Cargo CDG 1\"/>\n" +
                    "        <goods>\n" +
                    "            <ref type=\"AWB\" code=\"07712345678\"/>\n" +
                    "            <amount quantity=\"12.0\" weight=\"345.0\"/>\n" +
                    "            <description>ELECTRONICS</description>\n" +
                    "            <totalRefAmount quantity=\"12.0\" weight=\"345.0\"/>\n" +
                    "        </goods>\n" +
                    "        <customsStatus>X</customsStatus>\n" +
                    "        <customsDocument type=\"T1\" ref=\"098765432\"/>\n" +
                    "    </WarehouseMovementOut>\n" +
                    "</cargoMessage>\n";


    public static Transaction createTransaction(){
        Transaction transaction = new Transaction();
        transaction.setType(WAREHOUSE_MOVEMENT_IN);
        transaction.setMovementTime(MOVEMENT_TIME);
        transaction.setDeclaredInCode(DECLARED_IN_CODE);
        transaction.setDeclaredInLabel(DECLARED_IN_LABEL);
        transaction.setToCode(TO_CODE);
        transaction.setToLabel(TO_LABEL);
        transaction.setRefType(REF_TYPE);
        transaction.setRefCode(REF_CODE);
        transaction.setAmountQuantity(AMOUNT_QUANTITY);
        transaction.setAmountWeight(AMOUNT_WEIGHT);
        transaction.setDescription(DESCRIPTION);
        transaction.setTotalRefAmountQuantity(TOTAL_REF_QUANTITY);
        transaction.setTotalRefAmountWeight(TOTAL_REF_WEIGHT);
        transaction.setCustomsStatus(CUSTOMS_STATUS);
        transaction.setCustomsDocType(CUSTOMS_DOC_TYPE);
        transaction.setCustomsDocRef(CUSTOMS_DOC_REF);
        transaction.setUserInformations(USER);
        transaction.setMessageTime(MESSAGE_TIME);
        return transaction;
    }

}
