package com.pathbros.repositories;

import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import com.pathbros.models.Job;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface JobRepo extends JpaRepository<Job,Integer> {

    Optional<Job> findByJobIdAndJobIsActiveTrue(int jobId);

    List<Job> findAllByJobCityAndJobIsActiveTrue(String userCity);

    @Query("SELECT j FROM Job j WHERE j.jobIsActive = true "
            + "AND (:city IS NULL OR j.jobCity = :city) "
            + "AND (:title IS NULL OR j.jobTitle LIKE %:title%) "
            + "AND (:experience IS NULL OR j.jobExperience = :experience) "
            + "AND (:jobType IS NULL OR j.jobType = :jobType) "
            + "AND (:skill IS NULL OR :skill MEMBER OF j.jobSkillsRequired)"
            + "AND (:salary IS NULL OR j.jobSalaryRange LIKE %:salary%)")
    List<Job> filterJobs(
            @Param("city") String city,
            @Param("title") String title,
            @Param("experience") Experience experience,
            @Param("jobType") JobType jobType,
            @Param("skill") String skill

    );

    @Query("SELECT j FROM Job j ORDER BY j.jobPostedOn DESC")
    List<Job> findAllJobsNewest();


    @Query("SELECT j FROM Job j ORDER BY j.jobPostedOn ASC")
    List<Job> findAllJobsOldest();

    List<Job> findByJobOfCompanyIgnoreCaseAndJobIsActiveTrue(String companyName);


}
