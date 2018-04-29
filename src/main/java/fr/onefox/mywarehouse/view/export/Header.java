package fr.onefox.mywarehouse.view.export;

import fr.onefox.mywarehouse.domain.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"from", "to", "messageTime", "messageId"})
public class Header {

    public static final String CARGOINFO = "CARGOINFO";
    public static final String RAPIDCARGO = "RAPIDCARGO";

    @XmlAttribute
    private String to;

    @XmlAttribute
    private String messageTime;

    @XmlAttribute
    private String from;

    @XmlAttribute
    private String messageId;

    public Header(Transaction transaction) {
        this.to = CARGOINFO;
        this.from = RAPIDCARGO;
        this.messageTime = transaction.getMessageTimeToInstant();
        this.messageId = String.valueOf(transaction.get_id());
    }
}
