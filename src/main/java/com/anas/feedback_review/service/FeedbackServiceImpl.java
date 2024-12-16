package com.anas.feedback_review.service;

import com.anas.feedback_review.module.Feedback;
import com.anas.feedback_review.record.FeedbackRecord;
import com.anas.feedback_review.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void saveFeedback(FeedbackRecord feedbackRecord) {
        Feedback feedback = new Feedback();
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setComment(feedbackRecord.comment());
        feedback.setRating(feedbackRecord.rating());
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getApprovedFeedback() {
        return feedbackRepository.findByApproved(true);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> approveFeedback(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        feedback.ifPresent(f -> {
            f.setApproved(true);
            feedbackRepository.save(f);
        });
        return feedback;
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
