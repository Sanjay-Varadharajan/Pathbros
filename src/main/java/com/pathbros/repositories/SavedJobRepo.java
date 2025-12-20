package com.pathbros.repositories;

import com.pathbros.models.Job;
import com.pathbros.models.SavedJob;
import com.pathbros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedJobRepo extends JpaRepository<SavedJob,Integer> {

    List<SavedJob> findByUser(User user);


    Optional<SavedJob> findByUserAndJob(User user, Job job);

    List<SavedJob> findByUser_UserMail(String userMail);
}
