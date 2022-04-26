package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.Investment;
import com.gmail.burinigor7.crudservice.repository.FundraisingProjectRepository;
import com.gmail.burinigor7.crudservice.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final FundraisingProjectRepository fundraisingProjectRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository,
                             FundraisingProjectRepository fundraisingProjectRepository) {
        this.investmentRepository = investmentRepository;
        this.fundraisingProjectRepository = fundraisingProjectRepository;
    }

    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    public List<Investment> investmentsForFundraisingProject(Long fpId) {
        FundraisingProject fundraisingProject = fundraisingProjectRepository.findById(fpId).get();
        return investmentRepository.findAllByFundraisingProject(fundraisingProject);
    }
}
