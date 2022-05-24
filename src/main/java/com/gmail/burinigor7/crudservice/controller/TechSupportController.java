package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.Chat;
import com.gmail.burinigor7.crudservice.domain.Message;
import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.dto.ws.WSMessage;
import com.gmail.burinigor7.crudservice.service.TechSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tech-support")
public class TechSupportController {
    private final TechSupportService techSupportService;
    private final SimpMessagingTemplate messagingTemplate;
    private final SimpUserRegistry simpUserRegistry;

    @Autowired
    public TechSupportController(TechSupportService techSupportService,
                                 SimpMessagingTemplate simpMessagingTemplate,
                                 SimpUserRegistry simpUserRegistry) {
        this.techSupportService = techSupportService;
        this.messagingTemplate = simpMessagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
    }

    @PostMapping("/start-chat")
    public Chat startChat(@RequestParam String username) {
        return techSupportService.startChat(username);
    }

    @PatchMapping("/start-support/{chatId}")
    public Chat startTechSupport(@RequestParam String username, @PathVariable Long chatId) {
        return techSupportService.startSupport(username, chatId);
    }

    @PostMapping("/send-message/{chatId}")
    public Message sendMessage(@PathVariable Long chatId,
                               @RequestParam String username,
                               @RequestParam String content) {
        return techSupportService.saveMessage(chatId, username, content);
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload WSMessage message) {
        Message saved = techSupportService.saveMessageWS(message);
        User techSpecialist = techSupportService.getChatById(message.getChatId()).getTechSpecialist();
        if (techSpecialist != null) {
            if (message.getReceiverId() != null) {
                messagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiverId()),
                        "/queue/messages", saved);
            } else {
                messagingTemplate.convertAndSendToUser(String.valueOf(techSpecialist.getId()),
                        "/queue/messages", saved);
            }
        }
    }

    @PatchMapping("/mark-as-solved/{chatId}")
    public Chat markAsSolved(@PathVariable Long chatId,
                             @RequestParam String username) {
        return techSupportService.markAsSolved(chatId, username);
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> getChat(@PathVariable Long chatId) {
        return techSupportService.getChat(chatId);
    }

    @GetMapping("/all-chats")
    public List<Chat> chats(@RequestParam String techSpecUsername) {
        return techSupportService.chats(techSpecUsername);
    }

    @GetMapping("/unassigned-chats")
    public List<Chat> unassignedChats() {
        return techSupportService.unassignedChats();
    }

    @GetMapping("/{chatId}")
    public Chat chat(@PathVariable Long chatId) {
        return techSupportService.chat(chatId);
    }

    @GetMapping("/unresolved")
    public Chat unresolvedChat(@RequestParam String username) {
        return techSupportService.unresolvedChat(username);
    }
}
