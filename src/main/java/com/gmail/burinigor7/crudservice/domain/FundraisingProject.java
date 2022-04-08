package com.gmail.burinigor7.crudservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "fundraising_project")
public class FundraisingProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fundraising_project_id")
    private Long fundraisingProjectId;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "founder", nullable = false)
    private User founder;

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
