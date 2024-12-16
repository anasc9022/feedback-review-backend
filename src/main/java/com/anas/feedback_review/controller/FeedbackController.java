package com.anas.feedback_review.controller;

import com.anas.feedback_review.module.Feedback;
import com.anas.feedback_review.module.User;
import com.anas.feedback_review.record.FeedbackRecord;
import com.anas.feedback_review.service.CustomUserDetailsService;
import com.anas.feedback_review.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final CustomUserDetailsService customUserDetailsService;
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService, CustomUserDetailsService customUserDetailsService) {
        this.feedbackService = feedbackService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {

        Map<String, String> response = new HashMap<>();
        try {
            customUserDetailsService.registerUser(user);
            response.put("message", "User registered successfully!");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", "Username already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping
    public void submitFeedback(@RequestBody FeedbackRecord feedback) {
        feedbackService.saveFeedback(feedback);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getApprovedFeedback() {
        return ResponseEntity.ok(feedbackService.getApprovedFeedback());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Feedback> approveFeedback(@PathVariable Long id) {
        return feedbackService.approveFeedback(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

}
