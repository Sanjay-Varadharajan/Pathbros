    package com.pathbros.controller.controllerfororganisation;


    import com.pathbros.dtos.job.JobCreateDto;
    import com.pathbros.dtos.job.JobInactiveDto;
    import com.pathbros.service.organisationservice.JobServiceForOrganisation;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.security.Principal;

    @RestController
    @RequestMapping("/api/organisation")
    public class JobControllerForOrganisation {


       @Autowired
        JobServiceForOrganisation jobServiceForOrganisation;

        @PostMapping("/job/create")
        public ResponseEntity<String> createJob(Principal principal, @RequestBody JobCreateDto jobCreateDto){
            return jobServiceForOrganisation.createjob(principal,jobCreateDto);
        }

        @PatchMapping("job/close")
        public ResponseEntity<String> deactivateJob(Principal principal, @RequestBody JobInactiveDto jobInactiveDto){
            return jobServiceForOrganisation.deActivateJob(principal,jobInactiveDto);
        }


    }
