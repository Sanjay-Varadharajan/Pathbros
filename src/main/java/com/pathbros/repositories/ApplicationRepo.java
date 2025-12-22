package com.pathbros.repositories;

import com.pathbros.enums.ApplicationStatus;
import com.pathbros.models.Application;
import com.pathbros.models.Job;
import com.pathbros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepo extends JpaRepository<Application,Integer> {

        boolean existsByApplicantAndAppliedJob(User user, Job job);

        List<Application> findByApplicant_UserEmailAndApplicationStatus(String email, ApplicationStatus applicationStatus);

        Optional<Application> findByAppliedJob_JobIdAndApplicant_UserEmail(int jobId, String email);

        List<Application> findByApplicant_UserEmail(String Email);

        List<Application> findByApplicationOfCompany_companyEmail(String Email);
}
