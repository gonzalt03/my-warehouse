package fr.onefox.mywarehouse.view.export;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"quantity", "weight"})
public class Amount {

    @XmlAttribute
    private Double weight;

    @XmlAttribute
    private Double quantity;

    public Amount(Double weight, Double quantity) {
        this.weight = weight;
        this.quantity = quantity;
    }
}
