package fr.onefox.mywarehouse.view.export;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"type", "code"})
public class Ref {

    @XmlAttribute
    private String code;

    @XmlAttribute
    private String type;

    public Ref(String refType, String refCode) {
        this.code = refCode;
        this.type = refType;
    }
}
