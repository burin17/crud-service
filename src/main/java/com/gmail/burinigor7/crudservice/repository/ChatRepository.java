package com.gmail.burinigor7.crudservice.repository;

import com.gmail.burinigor7.crudservice.domain.Chat;
import com.gmail.burinigor7.crudservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByTechSpecialistAndIsSolvedIsFalse(User techSpecialist);

    @Query(nativeQuery = true,
    value = "select * " +
            "from chats " +
            "where tech_specialist is null")
    List<Chat> getAllUnassignedChats();

    @Query(nativeQuery = true,
            value = "select * " +
                    "from chats " +
                    "inner join users " +
                    "on users.user_id = chats.started_by " +
                    "where chats.is_solved = false " +
                    "and users.username = :username " +
                    "limit 1")
    Chat getUnresolvedChat(@Param("username") String username);
}


