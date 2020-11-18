package com.nextpay.risk_management.repository;

import com.google.common.base.Optional;
import com.nextpay.risk_management.model.RiskTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskTransRepo extends JpaRepository<RiskTransaction, Long> {
    @Query("SELECT e FROM RiskTransaction e WHERE e.productCode like ?1% ORDER BY e.created DESC")
    List<RiskTransaction> findAllByCode(String code, Pageable pageable);

    Optional<RiskTransaction> findByServiceCode(String code);
}
