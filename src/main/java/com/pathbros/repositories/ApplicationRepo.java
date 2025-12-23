package com.pathbros.repositories;

import com.pathbros.enums.ApplicationStatus;
import com.pathbros.models.Application;
import com.pathbros.models.Job;
import com.pathbros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

        Optional<Application> findByApplicationIdAndApplicationOfCompany_CompanyEmail(int applicationId, String companyMail);

        @Query("""
       SELECT a FROM Application a
       WHERE a.applicationOfCompany.companyEmail = :companyEmail
       AND (:status IS NULL OR a.applicationStatus = :status)
       AND (:collegeName IS NULL OR a.applicant.userCollegeName = :collegeName)
       """)
        List<Application> filterApplicationsBasic(
                @Param("companyEmail") String companyEmail,
                @Param("status") com.pathbros.enums.ApplicationStatus status,
                @Param("collegeName") String collegeName
        );

}
