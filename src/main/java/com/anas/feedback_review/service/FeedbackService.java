package com.anas.feedback_review.service;

import com.anas.feedback_review.module.Feedback;
import com.anas.feedback_review.record.FeedbackRecord;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    public void saveFeedback(FeedbackRecord feedback);

    public List<Feedback> getApprovedFeedback();

    public List<Feedback> getAllFeedback();

    public Optional<Feedback> approveFeedback(Long id);

    public void deleteFeedback(Long id);

    }
