package com.gmail.burinigor7.crudservice.repository;

import com.gmail.burinigor7.crudservice.domain.Chat;
import com.gmail.burinigor7.crudservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChat(Chat chat);
}
