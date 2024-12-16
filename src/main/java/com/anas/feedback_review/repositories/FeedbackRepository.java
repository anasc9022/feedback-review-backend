package com.anas.feedback_review.repositories;

import com.anas.feedback_review.module.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByApproved(boolean approved);
}
