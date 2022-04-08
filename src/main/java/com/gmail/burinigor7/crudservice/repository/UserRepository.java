package com.gmail.burinigor7.crudservice.repository;

import com.gmail.burinigor7.crudservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
