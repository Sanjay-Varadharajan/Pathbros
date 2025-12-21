package com.pathbros.controller;


import com.pathbros.dtos.savedjob.SavedJobDto;
import com.pathbros.service.BookMarkServiceForUser;
import com.pathbros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class BookmarkControllerforUser {

@Autowired
    BookMarkServiceForUser bookMarkServiceForUser;

    @PostMapping("jobs/{jobId}/bookmark")
    public ResponseEntity<String> bookMarkJobs(@PathVariable int jobId, Principal principal){
        return bookMarkServiceForUser.bookmarkjobs(jobId,principal);
    }

    @GetMapping("jobs/me/bookmarks")
    public ResponseEntity<List<SavedJobDto>> bookmarks(Principal principal){
        return bookMarkServiceForUser.bookmarks(principal);
    }

}
