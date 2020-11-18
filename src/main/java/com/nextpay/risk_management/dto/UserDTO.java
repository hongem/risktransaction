package com.nextpay.risk_management.dto;

import lombok.Data;
import java.util.ArrayList;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String accStatus;
    private ArrayList<String> role;

}
