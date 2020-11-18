package com.nextpay.risk_management.dto;

import com.nextpay.risk_management.model.RoleName;
import lombok.Data;
import java.util.ArrayList;


@Data
public class RoleDTO {
    private Long id;
    private RoleName name;
    private ArrayList<String> UserEmail;
}
