package com.gmail.burinigor7.crudservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fundraising_project")
public class FundraisingProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fundraising_project_id")
    private Long fundraisingProjectId;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "founder", nullable = false)
    private User founder;

    @OneToMany(mappedBy = "fundraisingProject")
    @JsonIgnore
    private List<PayoutRequest> payoutRequests;

    @OneToMany(mappedBy = "fundraisingProject")
    @JsonIgnore
    private List<Investment> investments;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FundraisingProjectStatus status;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duration", nullable = true)
    private Date duration;

    @Column(name = "days", nullable = false)
    private Integer days;

    @Column(name = "amountGoal", nullable = false)
    private Float amountGoal;

    @Column(name = "contract_address", nullable = true)
    private String contractAddress;

    @Column(name = "currentAmount", nullable = false)
    private Float currentAmount;

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<PayoutRequest> getPayoutRequests() {
        return payoutRequests;
    }

    public void setPayoutRequests(List<PayoutRequest> payoutRequests) {
        this.payoutRequests = payoutRequests;
    }

    public Float getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Float currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public Float getAmountGoal() {
        return amountGoal;
    }

    public void setAmountGoal(Float amountGoal) {
        this.amountGoal = amountGoal;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FundraisingProjectStatus getStatus() {
        return status;
    }

    public void setStatus(FundraisingProjectStatus status) {
        this.status = status;
    }

    public Long getFundraisingProjectId() {
        return fundraisingProjectId;
    }

    public void setFundraisingProjectId(Long fundraisingProjectId) {
        this.fundraisingProjectId = fundraisingProjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }
}
