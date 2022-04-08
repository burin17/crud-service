package com.gmail.burinigor7.crudservice.repository;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraisingProjectRepository extends JpaRepository<FundraisingProject, Long> {

}
