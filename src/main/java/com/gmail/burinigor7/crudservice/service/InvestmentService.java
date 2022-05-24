package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.Investment;
import com.gmail.burinigor7.crudservice.repository.FundraisingProjectRepository;
import com.gmail.burinigor7.crudservice.repository.InvestmentRepository;
import com.gmail.burinigor7.crudservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final FundraisingProjectRepository fundraisingProjectRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository,
                             FundraisingProjectRepository fundraisingProjectRepository,
                             UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.fundraisingProjectRepository = fundraisingProjectRepository;
        this.userRepository = userRepository;
    }

    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    public List<Investment> investmentsForFundraisingProject(Long fpId) {
        FundraisingProject fundraisingProject = fundraisingProjectRepository.findById(fpId).get();
        return investmentRepository.findAllByFundraisingProject(fundraisingProject);
    }

    public List<Investment> getInvestmentsForUser(Long userId) {
        return investmentRepository.findAllByInvestor(userRepository.findById(userId).get());
    }
}
