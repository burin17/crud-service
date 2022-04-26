package com.gmail.burinigor7.crudservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "investor", nullable = false)
    private User investor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fundraising_project", nullable = false)
    private FundraisingProject fundraisingProject;

    @Column(name = "eth_amount")
    private Float ethAmount;

    public Float getEthAmount() {
        return ethAmount;
    }

    public void setEthAmount(Float ethAmount) {
        this.ethAmount = ethAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInvestor() {
        return investor;
    }

    public void setInvestor(User investor) {
        this.investor = investor;
    }

    public FundraisingProject getFundraisingProject() {
        return fundraisingProject;
    }

    public void setFundraisingProject(FundraisingProject fundraisingProject) {
        this.fundraisingProject = fundraisingProject;
    }
}
