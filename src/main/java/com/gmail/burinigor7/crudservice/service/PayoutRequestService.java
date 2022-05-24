package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.PayoutRequest;
import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.repository.FundraisingProjectRepository;
import com.gmail.burinigor7.crudservice.repository.PayoutRequestRepository;
import com.gmail.burinigor7.crudservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class PayoutRequestService {
    private final PayoutRequestRepository payoutRequestRepository;
    private final FundraisingProjectRepository fundraisingProjectRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public PayoutRequestService(PayoutRequestRepository payoutRequestRepository,
                                FundraisingProjectRepository fundraisingProjectRepository,
                                UserRepository userRepository,
                                FileStorageService fileStorageService) {
        this.payoutRequestRepository = payoutRequestRepository;
        this.fundraisingProjectRepository = fundraisingProjectRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    public PayoutRequest create(PayoutRequest payoutRequest) {
        return payoutRequestRepository.save(payoutRequest);
    }

    public List<PayoutRequest> getAllForFundraisingProject(Long fundraisingProjectId) {
        FundraisingProject fundraisingProject= fundraisingProjectRepository.findById(fundraisingProjectId).get();
        return payoutRequestRepository.findAllByFundraisingProject(fundraisingProject);
    }

    public PayoutRequest payoutRequest(Long id) {
        return payoutRequestRepository.findById(id).get();
    }

    public PayoutRequest approve(Long userId, Long prId, Integer countOfApproves) {
        PayoutRequest payoutRequest = payoutRequestRepository.findById(prId).get();
        User user = userRepository.findById(userId).get();
        List<User> approvers = payoutRequest.getApprovers();
        approvers.add(user);
        payoutRequest.setApprovers(approvers);
        payoutRequest.setCountOfApproves(countOfApproves);
        return payoutRequestRepository.save(payoutRequest);
    }

    public PayoutRequest addReport(Long payoutReqId, String notes, MultipartFile[] files) {
        if (files != null) {
            Arrays.stream(files).forEach(file -> fileStorageService.saveReportFile(file, payoutReqId));
        }
        PayoutRequest payoutRequest = payoutRequestRepository.findById(payoutReqId).get();
        payoutRequest.setReportNotes(notes);
        return payoutRequestRepository.save(payoutRequest);
    }
}
