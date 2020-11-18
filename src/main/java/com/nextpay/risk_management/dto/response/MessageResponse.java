package com.nextpay.risk_management.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageResponse {
    private int status;
    private String message;

}
