package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.FundraisingProjectStatus;
import com.gmail.burinigor7.crudservice.repository.FundraisingProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        fundraisingProject.setStatus(FundraisingProjectStatus.NOT_CONSIDERED);
        return fundraisingProjectRepository.save(fundraisingProject);
    }

    public FundraisingProject updateFundraisingProject(FundraisingProject fundraisingProject) {
        fundraisingProject.setFounder(fundraisingProject(fundraisingProject.getFundraisingProjectId()).getFounder());
        return fundraisingProjectRepository.save(fundraisingProject);
    }

    public List<FundraisingProject> getFundraisingProjectsByTitlePiece(String titlePiece) {
        return fundraisingProjectRepository.getFundraisingProjectsByTitlePiece(titlePiece);
    }

    public Map<String, String> validate(FundraisingProject fundraisingProject) {
        Map<String, String> errMap = new HashMap<>();
        if (fundraisingProjectRepository.getFundraisingProjectsByTitle(fundraisingProject.getTitle()).isPresent()) {
            errMap.put("title", "Specified title already in use");
        }
        return errMap;
    }

    public List<FundraisingProject> fundraisingProjectsByUsername(String username) {
        return fundraisingProjectRepository.getFundraisingProjectsByFounderUsername(username);
    }

    public List<FundraisingProject> all() {
        List<FundraisingProject> all = fundraisingProjectRepository.findAll();
        System.out.println(all);
        return all;
    }

    public List<FundraisingProject> fundraisingProjectsVisibleForUser() {
        return fundraisingProjectRepository.getFundraisingProjectsVisibleForUser();
    }
}
