package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.repository.FundraisingProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundraisingProjectService {
    private final FundraisingProjectRepository fundraisingProjectRepository;

    @Autowired
    public FundraisingProjectService(FundraisingProjectRepository fundraisingProjectRepository) {
        this.fundraisingProjectRepository = fundraisingProjectRepository;
    }

    public FundraisingProject fundraisingProject(Long fundraisingProjectId) {
        return fundraisingProjectRepository.findById(fundraisingProjectId).get();
    }

    public FundraisingProject createFundraisingProject(FundraisingProject fundraisingProject) {
        return fundraisingProjectRepository.save(fundraisingProject);
    }
}
