package com.nextpay.risk_management.repository;

import com.nextpay.risk_management.model.Role;
import com.nextpay.risk_management.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role,Long> {

    Optional<Role> findByName(RoleName name);

    Optional<Role> findById(Long id);
}
