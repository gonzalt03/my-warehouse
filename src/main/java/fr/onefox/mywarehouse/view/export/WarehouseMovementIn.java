package fr.onefox.mywarehouse.view.export;

import fr.onefox.mywarehouse.domain.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"movementTime", "declaredIn", "to", "goods", "customsStatus"})
public class WarehouseMovementIn {

    private LabelCode to;

    private String movementTime;

    private LabelCode declaredIn;

    private Goods goods;

    private String customsStatus;

    public WarehouseMovementIn(Transaction transaction) {
        this.to = new LabelCode(transaction.getToLabel(), transaction.getToCode());
        this.declaredIn = new LabelCode(transaction.getDeclaredInLabel(), transaction.getDeclaredInCode());
        this.movementTime = transaction.getMovementTimeToInstant();
        this.goods = new Goods(transaction);
        this.customsStatus = String.valueOf(transaction.getCustomsStatus());
    }
}
