package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.FundraisingProjectStatus;
import com.gmail.burinigor7.crudservice.service.FundraisingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundraising-projects")
public class FundraisingProjectController {
    private final FundraisingProjectService fundraisingProjectService;

    @Autowired
    public FundraisingProjectController(FundraisingProjectService fundraisingProjectService) {
        this.fundraisingProjectService = fundraisingProjectService;
    }

    @GetMapping("{fundraisingProjectId}")
    public FundraisingProject fundraisingProject(@PathVariable Long fundraisingProjectId) {
        return fundraisingProjectService.fundraisingProject(fundraisingProjectId);
    }

    @GetMapping("/visibleForUser")
    public List<FundraisingProject> fundraisingProjectsByStatus() {
        return fundraisingProjectService.fundraisingProjectsVisibleForUser();
    }

    @PostMapping
    public FundraisingProject createFundraisingProject(@RequestBody FundraisingProject fundraisingProject) {
        return fundraisingProjectService.createFundraisingProject(fundraisingProject);
    }

    @GetMapping
    public List<FundraisingProject> fundraisingProjectsByTitlePiece(@RequestParam String titlePiece) {
        return fundraisingProjectService.getFundraisingProjectsByTitlePiece(titlePiece);
    }

    @PostMapping("/validate")
    public Map<String, String> validate(@RequestBody FundraisingProject fundraisingProject) {
        return fundraisingProjectService.validate(fundraisingProject);
    }

    @GetMapping("/username")
    public List<FundraisingProject> fundraisingProjectsByUsername(@RequestParam String username) {
        return fundraisingProjectService.fundraisingProjectsByUsername(username);
    }

    @PutMapping
    public FundraisingProject updateFundraisingProject(@RequestBody FundraisingProject fundraisingProject) {
        return fundraisingProjectService.updateFundraisingProject(fundraisingProject);
    }

    @GetMapping("/all")
    public List<FundraisingProject> all() {
        return fundraisingProjectService.all();
    }
}
