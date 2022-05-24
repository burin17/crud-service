package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.FundraisingProjectStatus;
import com.gmail.burinigor7.crudservice.dto.FileInfo;
import com.gmail.burinigor7.crudservice.service.FileStorageService;
import com.gmail.burinigor7.crudservice.service.FundraisingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fundraising-projects")
public class FundraisingProjectController {
    private final FundraisingProjectService fundraisingProjectService;
    private final FileStorageService fileStorageService;

    @Autowired
    public FundraisingProjectController(FundraisingProjectService fundraisingProjectService,
                                        FileStorageService fileStorageService) {
        this.fundraisingProjectService = fundraisingProjectService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("{fundraisingProjectId}")
    public FundraisingProject fundraisingProject(@PathVariable Long fundraisingProjectId) {
        return fundraisingProjectService.fundraisingProject(fundraisingProjectId);
    }

    @GetMapping("/visibleForUser")
    public List<FundraisingProject> fundraisingProjectsByStatus() {
        return fundraisingProjectService.fundraisingProjectsVisibleForUser();
    }

    @GetMapping("/status/{status}")
    public List<FundraisingProject> getFundraisingProjectByStatus(@PathVariable FundraisingProjectStatus status) {
        return fundraisingProjectService.byStatus(status);
    }

    @PostMapping
    public FundraisingProject createFundraisingProject(@RequestBody FundraisingProject fundraisingProject) {
        return fundraisingProjectService.createFundraisingProject(fundraisingProject);
    }

    @PostMapping("/upload-images/{fpId}")
    public ResponseEntity<?> uploadFundraisingProjectImages(@PathVariable Long fpId,
                                                            @RequestParam("files") MultipartFile[] images) {
        Arrays.stream(images).forEach(image -> fileStorageService.saveImage(image, fpId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload-moderation/{fpId}")
    public ResponseEntity<?> uploadModerationFiles(@PathVariable Long fpId,
                                                   @RequestParam("files") MultipartFile[] files) {
        Arrays.stream(files).forEach(file -> fileStorageService.saveModerationFile(file, fpId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload-other/{fpId}")
    public ResponseEntity<?> uploadOtherFiles(@PathVariable Long fpId,
                                                   @RequestParam("files") MultipartFile[] files) {
        Arrays.stream(files).forEach(file -> fileStorageService.saveOtherFile(file, fpId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/moderation-files/{fpId}")
    public ResponseEntity<List<FileInfo>> getModerationFiles(@PathVariable Long fpId) {
        List<FileInfo> fileInfos = fileStorageService.loadAllModerationFiles(fpId).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FundraisingProjectController.class, "getFile", "moderation" + File.separator + fpId + File.separator + path.getFileName()
                            .toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/other-files/{fpId}")
    public ResponseEntity<List<FileInfo>> getOtherFiles(@PathVariable Long fpId) {
        List<FileInfo> fileInfos = fileStorageService.loadAllOtherFiles(fpId).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FundraisingProjectController.class, "getFile", "other" + File.separator + fpId + File.separator + path.getFileName()
                            .toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/image-files/{fpId}")
    public ResponseEntity<List<FileInfo>> getImages(@PathVariable Long fpId) {
        List<FileInfo> fileInfos = fileStorageService.loadAllImages(fpId).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FundraisingProjectController.class, "getFile", "images" + File.separator + fpId + File.separator + path.getFileName()
                            .toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile(@RequestParam String path) {
        Resource file = fileStorageService.load(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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

    @GetMapping("/invested")
    public List<FundraisingProject> invested(@RequestParam Long investor) {
        return fundraisingProjectService.invested(investor);
    }

    @GetMapping("/tags/{projId}")
    public List<String> getTags(@PathVariable Long projId) {
        return fundraisingProjectService.getTags(projId);
    }

    @GetMapping("/search/{titlePiece}")
    public List<FundraisingProject> search(@PathVariable String titlePiece, @RequestParam String[] tags) {
        return fundraisingProjectService.search(titlePiece, tags);
    }

    @GetMapping("/search-by-tags")
    public List<FundraisingProject> search(@RequestParam String[] tags) {
        return fundraisingProjectService.searchByTags(tags);
    }
}
