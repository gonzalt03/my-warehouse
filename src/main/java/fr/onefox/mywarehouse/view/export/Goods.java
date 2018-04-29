package fr.onefox.mywarehouse.view.export;

import fr.onefox.mywarehouse.domain.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ref", "amount", "description", "totalRefAmount"})
public class Goods {

    private Amount amount;

    private Amount totalRefAmount;

    private Ref ref;

    private String description;

    public Goods(Transaction transaction) {
        this.amount = new Amount(transaction.getAmountWeight(), transaction.getAmountQuantity());
        this.totalRefAmount = new Amount(transaction.getTotalRefAmountWeight(), transaction.getTotalRefAmountQuantity());
        this.ref = new Ref(transaction.getRefType(), transaction.getRefCode());
        this.description = transaction.getDescription();
    }
}
