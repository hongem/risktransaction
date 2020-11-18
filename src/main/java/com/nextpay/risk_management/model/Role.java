package com.nextpay.risk_management.model;

import lombok.*;
import javax.persistence.*;


@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name",length = 20,unique=true)
    private RoleName name;

}
