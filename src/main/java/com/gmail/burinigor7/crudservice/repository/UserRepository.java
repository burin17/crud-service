package com.gmail.burinigor7.crudservice.repository;

import com.gmail.burinigor7.crudservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);

    @Query(nativeQuery = true,
    value = "select * " +
            "from users " +
            "where lower(username) like lower(concat('%', :piece, '%'))")
    List<User> getByUsernamePiece(@Param("piece") String piece);
}
