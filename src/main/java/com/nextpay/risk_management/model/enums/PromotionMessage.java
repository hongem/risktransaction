package com.nextpay.risk_management.model.enums;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;

//@Data
public class PromotionMessage implements Serializable {

    @NotNull
    private ActorInfo actor;
    @NotNull
    private ServiceInfo service;
    @NotNull
    private ReqInfo req;
    private HashMap<String, String> optionalData;
//
//    public PromotionMessage(ServiceReqVas serviceReqVas) {
//        ActorInfo actorInfo = new ActorInfo(serviceReqVas.getSenderCustomer(), serviceReqVas.getSenderUser().getId());
//        ServiceInfo serviceInfo = new ServiceInfo(serviceReqVas.getServiceVas());
//        ReqInfo reqInfo = new ReqInfo(serviceReqVas);
//        this.actor = actorInfo;
//        this.service = serviceInfo;
//        this.req = reqInfo;
//        this.optionalData = new HashMap<>();
//    }

}

