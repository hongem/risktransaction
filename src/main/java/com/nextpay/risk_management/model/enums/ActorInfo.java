package com.nextpay.risk_management.model.enums;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

//@Data
class ActorInfo {
    @NotNull
    private Long id;
    @NotEmpty
    private String code;

    private String email;

    private String phoneNumber;

    private EnumCustomerType type;

    private EnumCustomerStatus status;

    @NotEmpty
    private String fullname;

    private Long userId;

//    ActorInfo(Customer customer, long userId) {
//        BeanUtils.copyProperties(customer, this);
//        this.userId = userId;
//    }

}

