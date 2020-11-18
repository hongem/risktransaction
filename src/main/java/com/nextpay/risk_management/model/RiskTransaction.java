package com.nextpay.risk_management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.OffsetDateTime;



@Entity
@Data
@Table(name = "risktransaction")
public class RiskTransaction {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy HH:mm:ss")
    private OffsetDateTime created;

    @Column(name = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy HH:mm:ss")
    private OffsetDateTime updated;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "sender_cus_id")
    private Long sennderCusId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "sender_fee")
    private Double senderFee;

    @Column(name = "receiver_fee")
    private Double receiverFee;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "status")
    private String status;

    @Column(name = "vas_product_id")
    private Long vasProductId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "vas_quantity")
    private String vasQuantity;

}