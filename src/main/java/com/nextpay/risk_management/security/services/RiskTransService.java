package com.nextpay.risk_management.security.services;

import com.nextpay.risk_management.model.RiskTransaction;
import com.nextpay.risk_management.repository.RiskTransRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RiskTransService {

    @Autowired
    RiskTransRepo riskTransRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<RiskTransaction> getAllRisk(int page, int item){
        List<RiskTransaction> riskTransactions = riskTransRepo.findAll(PageRequest.of((page-1), item, Sort.by("created").descending())).getContent();
        return riskTransactions;
    }

    public List<RiskTransaction> getRiskByCode(String code, int page, int item){
        List<RiskTransaction> riskTransactions = riskTransRepo.findAllByCode(code, PageRequest.of((page-1), item));
        return riskTransactions;
    }

}
