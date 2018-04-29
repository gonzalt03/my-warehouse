package fr.onefox.mywarehouse.view.export;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"type", "ref"})
public class CustomsDocument {

    @XmlAttribute
    private String ref;

    @XmlAttribute
    private String type;

    public CustomsDocument(String customsDocRef, String customsDocType) {
        this.ref = customsDocRef;
        this.type = customsDocType;
    }

}
