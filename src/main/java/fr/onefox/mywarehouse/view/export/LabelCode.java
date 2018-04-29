package fr.onefox.mywarehouse.view.export;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"code", "label"})
public class LabelCode {

    @XmlAttribute
    private String label;

    @XmlAttribute
    private String code;

    public LabelCode(String label, String code) {
        this.label = label;
        this.code = code;
    }
}
