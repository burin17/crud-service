package com.gmail.burinigor7.crudservice.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "payout_request")
public class PayoutRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "eth_amount", nullable = false)
    private Float ethAmount;
    @Column(name = "intention", nullable = false)
    private String intention;
    @Column(name = "reporting", nullable = false)
    private String reporting;
    @Column(name = "achieve_by", nullable = false)
    private Date achieveBy;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fundraising_project", nullable = false)
    private FundraisingProject fundraisingProject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "payout_request_approvers",
            joinColumns = @JoinColumn(name = "payout_request_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> approvers;
    @Column(name = "required_amount_of_approves", nullable = true)
    private Integer requiredAmountOfApproves;

    @Column(name = "request_idx", nullable = true)
    private String requestIdx;

    @Column(name = "count_of_approves")
    private Long countOfApproves;

    public Long getCountOfApproves() {
        return countOfApproves;
    }

    public void setCountOfApproves(Long countOfApproves) {
        this.countOfApproves = countOfApproves;
    }

    public Integer getRequiredAmountOfApproves() {
        return requiredAmountOfApproves;
    }

    public void setRequiredAmountOfApproves(Integer requiredAmountOfApproves) {
        this.requiredAmountOfApproves = requiredAmountOfApproves;
    }

    public List<User> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<User> approvers) {
        this.approvers = approvers;
    }

    public String getRequestIdx() {
        return requestIdx;
    }

    public void setRequestIdx(String contractAddress) {
        this.requestIdx = contractAddress;
    }

    public FundraisingProject getFundraisingProject() {
        return fundraisingProject;
    }

    public void setFundraisingProject(FundraisingProject fundraisingProject) {
        this.fundraisingProject = fundraisingProject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getEthAmount() {
        return ethAmount;
    }

    public void setEthAmount(Float ethAmount) {
        this.ethAmount = ethAmount;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getReporting() {
        return reporting;
    }

    public void setReporting(String reporting) {
        this.reporting = reporting;
    }

    public Date getAchieveBy() {
        return achieveBy;
    }

    public void setAchieveBy(Date achieveBy) {
        this.achieveBy = achieveBy;
    }
}
