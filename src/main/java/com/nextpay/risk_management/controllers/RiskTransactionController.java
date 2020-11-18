package com.nextpay.risk_management.controllers;

import com.nextpay.risk_management.model.RiskTransaction;
import com.nextpay.risk_management.repository.RiskTransRepo;
import com.nextpay.risk_management.security.services.RiskTransService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/risktransaction")
public class RiskTransactionController {

    @Autowired
    RiskTransRepo riskTransRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RiskTransService riskTransService;


    @GetMapping("/getRisk")
    public List<RiskTransaction> getList(@RequestParam("code") String code, @RequestParam("page") int page, @RequestParam("item") int item){
        if(code.equals("ALL")){
            return  riskTransService.getAllRisk(page,item);
        }
        else{
            return  riskTransService.getRiskByCode(code,page,item);
        }
    }

}
