package com.project.dugeun.domain.likeablePerson.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeablePersonController {


    @GetMapping("/users/{userId}/like")
    public ResponseEntity like(@PathVariable String userId){


    }
}
