package fr.onefox.mywarehouse.view.export;

import fr.onefox.mywarehouse.domain.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"customsDocument"})
public class WarehouseMovementOut extends WarehouseMovementIn {

    private CustomsDocument customsDocument;

    public WarehouseMovementOut(Transaction transaction) {
        super(transaction);
        this.customsDocument = new CustomsDocument(transaction.getCustomsDocRef(), transaction.getCustomsDocType());
    }
}
