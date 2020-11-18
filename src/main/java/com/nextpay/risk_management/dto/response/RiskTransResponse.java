package com.nextpay.risk_management.dto.response;


import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class RiskTransResponse implements Serializable {

    @Id
    private Long id;

    private String productCode;

    private String productName;

    private OffsetDateTime created;
}
