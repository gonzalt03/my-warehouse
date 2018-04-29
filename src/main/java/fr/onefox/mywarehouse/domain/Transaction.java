package fr.onefox.mywarehouse.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
public class Transaction {

    private ZoneId defaultZoneId = ZoneId.of("Europe/Paris");

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @NotNull
    @ApiModelProperty(required = true, value = "WAREHOUSE_MOVEMENT_IN")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty(required = true, value = "2018-03-01T22:20:07.200Z")
    private LocalDateTime movementTime;

    @ApiModelProperty(hidden = true)
    private LocalDateTime messageTime = LocalDateTime.now();

    @NotNull
    @ApiModelProperty(required = true, value = "Thomas Gonzalez")
    private String userInformations;

    @NotNull
    @ApiModelProperty(required = true, value = "CDGRC1")
    private String declaredInCode;

    @NotNull
    @ApiModelProperty(required = true, value = "RapidCargo CDG")
    private String declaredInLabel;

    @NotNull
    @ApiModelProperty(required = true, value = "CDGAF1")
    private String toCode;

    @NotNull
    @ApiModelProperty(required = true, value = "Air Cargo CDG 1")
    private String toLabel;

    @NotNull
    @ApiModelProperty(required = true, value = "AWB")
    private String refType;

    @NotNull
    @ApiModelProperty(required = true, value = "07712345678")
    private String refCode;

    @NotNull
    @ApiModelProperty(required = true, value = "12")
    private Double amountQuantity;

    @NotNull
    @ApiModelProperty(required = true, value = "345")
    private Double amountWeight;

    @NotNull
    @ApiModelProperty(required = true, value = "ELECTRONICS")
    private String description;

    @NotNull
    @ApiModelProperty(required = true, value = "12")
    private Double totalRefAmountQuantity;

    @NotNull
    @ApiModelProperty(required = true, value = "345")
    private Double totalRefAmountWeight;

    @NotNull
    @ApiModelProperty(required = true, value = "X")
    private char customsStatus;

    @ApiModelProperty(required = false, value = "T1")
    private String customsDocType;

    @ApiModelProperty(required = false, value = "098765432")
    private String customsDocRef;

    public String getMovementTimeToInstant() {
        return movementTime.atZone(defaultZoneId).toInstant().toString();
    }

    public String getMessageTimeToInstant() {
        return messageTime.atZone(defaultZoneId).toInstant().toString();
    }
}
