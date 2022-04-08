package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.service.FundraisingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public FundraisingProject createFundraisingProject(@RequestBody FundraisingProject fundraisingProject) {
        return fundraisingProjectService.createFundraisingProject(fundraisingProject);
    }
}
