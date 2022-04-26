package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.Investment;
import com.gmail.burinigor7.crudservice.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    private final InvestmentService investmentService;

    @Autowired
    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public Investment create(@RequestBody Investment investment) {
        return investmentService.saveInvestment(investment);
    }

    @GetMapping
    public List<Investment> getInvestments(@RequestParam Long fpId) {
        return investmentService.investmentsForFundraisingProject(fpId);
    }
}
