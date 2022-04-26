package com.gmail.burinigor7.crudservice.repository;

import com.gmail.burinigor7.crudservice.domain.FundraisingProject;
import com.gmail.burinigor7.crudservice.domain.FundraisingProjectStatus;
import com.gmail.burinigor7.crudservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundraisingProjectRepository extends JpaRepository<FundraisingProject, Long> {

    @Query(value = "select * " +
            "from fundraising_project f " +
            "where lower(f.title) like lower(concat(:title, '%')) ",
           nativeQuery = true)
    List<FundraisingProject> getFundraisingProjectsByTitlePiece(@Param("title") String title);

    Optional<FundraisingProject> getFundraisingProjectsByTitle(String title);

    List<FundraisingProject> getFundraisingProjectsByFounderUsername(String username);

    @Query(nativeQuery = true,
    value = "SELECT * " +
            "FROM fundraising_project " +
            "WHERE status = 'IN_PROGRESS' " +
            "OR status = 'FINANCED'")
    List<FundraisingProject> getFundraisingProjectsVisibleForUser();

    @Query(nativeQuery = true,
        value = "select fp.* " +
                "from fundraising_project fp " +
                "inner join investments i " +
                "on fp.fundraising_project_id = i.fundraising_project " +
                "where i.investor = :investor ")
    List<FundraisingProject> getInvested(@Param("investor") Long id);
}
