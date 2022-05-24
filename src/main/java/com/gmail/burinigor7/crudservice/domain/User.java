package com.gmail.burinigor7.crudservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "patronymic", nullable = true)
    private String patronymic;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "encr_pass", nullable = false)
    private String password;
    @OneToMany(mappedBy = "founder")
    @JsonIgnore
    private List<FundraisingProject> fundraisingProjects;
    @OneToMany(mappedBy = "fundraisingProject")
    @JsonIgnore
    private List<Investment> investments;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "approvers")
    @JsonIgnore
    private List<PayoutRequest> approvedPayoutRequest;
    @OneToMany(mappedBy = "startedBy")
    @JsonIgnore
    private List<Chat> startedChats;
    @OneToMany(mappedBy = "techSpecialist")
    @JsonIgnore
    private List<Chat> helpChats;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Message> typedMessages;

    public List<PayoutRequest> getApprovedPayoutRequest() {
        return approvedPayoutRequest;
    }

    public void setApprovedPayoutRequest(List<PayoutRequest> approvedPayoutRequest) {
        this.approvedPayoutRequest = approvedPayoutRequest;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }

    public List<FundraisingProject> getFundraisingProjects() {
        return fundraisingProjects;
    }

    public void setFundraisingProjects(List<FundraisingProject> fundraisingProjects) {
        this.fundraisingProjects = fundraisingProjects;
    }

    public List<Chat> getStartedChats() {
        return startedChats;
    }

    public void setStartedChats(List<Chat> startedChats) {
        this.startedChats = startedChats;
    }

    public List<Chat> getHelpChats() {
        return helpChats;
    }

    public void setHelpChats(List<Chat> helpChats) {
        this.helpChats = helpChats;
    }

    public List<Message> getTypedMessages() {
        return typedMessages;
    }

    public void setTypedMessages(List<Message> typedMessages) {
        this.typedMessages = typedMessages;
    }
}
