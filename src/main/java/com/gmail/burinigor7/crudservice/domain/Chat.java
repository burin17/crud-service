package com.gmail.burinigor7.crudservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @JoinColumn(name = "started_by", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User startedBy;

    @JoinColumn(name = "tech_specialist", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    private User techSpecialist;

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private Set<Message> messages;

    @Column(name = "is_solved", nullable = false)
    private boolean isSolved;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public User getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(User startedBy) {
        this.startedBy = startedBy;
    }

    public User getTechSpecialist() {
        return techSpecialist;
    }

    public void setTechSpecialist(User techSpecialist) {
        this.techSpecialist = techSpecialist;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }
}
