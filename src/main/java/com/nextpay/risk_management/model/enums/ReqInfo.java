package com.nextpay.risk_management.model.enums;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

//@Data
class ReqInfo {
    @NotNull
    private Long id;
    @NotEmpty
    private String code;

    private OffsetDateTime created;

    private Long createdBy;

    private OffsetDateTime updated;

    private Long updatedBy;


    private PaymentMethod paymentMethod;


    private Double amount;

    private Double senderFee;

    private Double receiverFee;

    private Double discountAmount;

    private String currencyCode;

    @NotEmpty
    private EnumServiceReqVasStatus status;

//    ReqInfo(ServiceReqVas serviceReqVas) {
//        this.paymentMethod = new PaymentMethod();
//        paymentMethod.setBankId(serviceReqVas.getSenderBank().getId());
//        paymentMethod.setBankCode(serviceReqVas.getSenderBank().getCode());
//        paymentMethod.setMethodCode(serviceReqVas.getSenderMethod().getCode().name());
//        paymentMethod.setMethodId(serviceReqVas.getSenderMethod().getId());
//
//        BeanUtils.copyProperties(serviceReqVas, this);
//
//    }
}
