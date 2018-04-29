package fr.onefox.mywarehouse.view.export;

import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.domain.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"header", "warehouseMovementOut", "warehouseMovementIn"})
public class CargoMessage {

    @XmlElement(name = "Header")
    private Header header;

    @XmlAttribute
    private String type;

    @XmlElement(name = "WarehouseMovementOut")
    private WarehouseMovementOut warehouseMovementOut;

    @XmlElement(name = "WarehouseMovementIn")
    private WarehouseMovementIn warehouseMovementIn;

    public CargoMessage(Transaction transaction) {
        this.header = new Header(transaction);
        this.type = transaction.getType().getValue();
        if (transaction.getType().equals(TransactionType.WAREHOUSE_MOVEMENT_OUT)) {
            this.warehouseMovementOut = new WarehouseMovementOut(transaction);
        } else {
            this.warehouseMovementIn = new WarehouseMovementIn(transaction);
        }
    }

}
