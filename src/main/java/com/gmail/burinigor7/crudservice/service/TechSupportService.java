package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.Chat;
import com.gmail.burinigor7.crudservice.domain.Message;
import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.dto.ws.WSMessage;
import com.gmail.burinigor7.crudservice.repository.ChatRepository;
import com.gmail.burinigor7.crudservice.repository.MessageRepository;
import com.gmail.burinigor7.crudservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TechSupportService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public TechSupportService(UserRepository userRepository,
                              ChatRepository chatRepository,
                              MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public Chat startChat(String username) {
        User startedBy = userRepository.getUserByUsername(username).get();
        Chat chat = new Chat();
        chat.setStartedBy(startedBy);
        return chatRepository.save(chat);
    }

    public Chat startSupport(String username, Long chatId) {
        User techSpecialist = userRepository.getUserByUsername(username).get();
        Chat chat = chatRepository.findById(chatId).get();
        chat.setTechSpecialist(techSpecialist);
        return chatRepository.save(chat);
    }

    public Message saveMessage(Long chatId, String username, String content) {
        Chat chat = chatRepository.findById(chatId).get();
        if (chat.getStartedBy().getUsername().equals(username) || chat.getTechSpecialist().getUsername().equals(username)) {
            User sender = userRepository.getUserByUsername(username).get();
            Message message = new Message();
            message.setAuthor(sender);
            message.setChat(chat);
            message.setContent(content);
            return messageRepository.save(message);
        }
        return null;
    }

    public Message saveMessageWS(WSMessage message) {
        Chat chat = chatRepository.findById(message.getChatId()).get();
        User sender = userRepository.findById(message.getSenderId()).get();
        Message savedMessage = new Message();
        savedMessage.setAuthor(sender);
        savedMessage.setChat(chat);
        savedMessage.setContent(message.getContent());
        return messageRepository.save(savedMessage);
    }

    public Chat getChatById(Long id) {
        return chatRepository.findById(id).get();
    }

    public Chat markAsSolved(Long chatId, String username) {
        Chat chat = chatRepository.findById(chatId).get();
        if (chat.getStartedBy().getUsername().equals(username)) {
            chat.setSolved(true);
            return chatRepository.save(chat);
        }
        return chat;
    }

    public List<Message> getChat(Long chatId) {
        return messageRepository.findAllByChat(chatRepository.findById(chatId).get());
    }

    public List<Chat> chats(String username) {
        return chatRepository.findAllByTechSpecialistAndIsSolvedIsFalse(userRepository.getUserByUsername(username).get());
    }

    public List<Chat> unassignedChats() {
        return chatRepository.getAllUnassignedChats();
    }

    public Chat chat(Long chatId) {
        return chatRepository.findById(chatId).get();
    }

    public Chat unresolvedChat(String username) {
        return chatRepository.getUnresolvedChat(username);
    }
}
