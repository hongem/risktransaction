package com.nextpay.risk_management.model.enums;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

//@Data
class ServiceInfo {
    @NotNull
    private Long id;
    @NotEmpty
    private EnumVimoServiceCode code;
    @NotEmpty
    private EnumVimoServiceType type;

    private EnumVimoServiceGroup serviceGroup;


    ServiceInfo(ServiceBase serviceBase) {
        BeanUtils.copyProperties(serviceBase, this);

    }
}
