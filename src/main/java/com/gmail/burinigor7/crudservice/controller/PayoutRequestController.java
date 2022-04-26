package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.PayoutRequest;
import com.gmail.burinigor7.crudservice.service.PayoutRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payout-requests")
public class PayoutRequestController {
    private final PayoutRequestService payoutRequestService;

    @Autowired
    public PayoutRequestController(PayoutRequestService payoutRequestService) {
        this.payoutRequestService = payoutRequestService;
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
                                 @RequestParam Long countOfApproves) {
        return payoutRequestService.approve(userId, prId, countOfApproves);
    }
}
