package com.gmail.burinigor7.crudservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @JoinColumn(name = "chat", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Chat chat;

    @JoinColumn(name = "author", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
