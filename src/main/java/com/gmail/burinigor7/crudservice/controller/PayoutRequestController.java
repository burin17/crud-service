package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.PayoutRequest;
import com.gmail.burinigor7.crudservice.dto.FileInfo;
import com.gmail.burinigor7.crudservice.service.FileStorageService;
import com.gmail.burinigor7.crudservice.service.PayoutRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payout-requests")
public class PayoutRequestController {
    private final PayoutRequestService payoutRequestService;
    private final FileStorageService fileStorageService;

    @Autowired
    public PayoutRequestController(PayoutRequestService payoutRequestService,
                                   FileStorageService fileStorageService) {
        this.payoutRequestService = payoutRequestService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public PayoutRequest create(@RequestBody PayoutRequest payoutRequest) {
        return payoutRequestService.create(payoutRequest);
    }

    @GetMapping("/{fundraisingProjectId}")
    public List<PayoutRequest> all(@PathVariable Long fundraisingProjectId) {
        return payoutRequestService.getAllForFundraisingProject(fundraisingProjectId);
    }

    @GetMapping
    public PayoutRequest payoutRequest(@RequestParam Long id) {
        return payoutRequestService.payoutRequest(id);
    }

    @PostMapping("/approve")
    public PayoutRequest approve(@RequestParam Long userId, @RequestParam Long prId,
                                 @RequestParam Integer countOfApproves) {
        return payoutRequestService.approve(userId, prId, countOfApproves);
    }

    @PutMapping("/addReport/{id}")
    public PayoutRequest addReport(@PathVariable Long id,
                                   @RequestParam String notes,
                                   @RequestParam(value = "files", required = false) MultipartFile[] files) {
        return payoutRequestService.addReport(id, notes, files);
    }

    @GetMapping("/report-files/{id}")
    public ResponseEntity<List<FileInfo>> getReportFiles(@PathVariable Long id) {
        List<FileInfo> fileInfos = fileStorageService.loadAllReportFiles(id).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FundraisingProjectController.class, "getFile", "report" + File.separator + id + File.separator + path.getFileName()
                            .toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
}
