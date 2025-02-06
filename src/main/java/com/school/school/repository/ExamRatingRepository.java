package com.school.school.repository;

import com.school.school.model.ExamRating;
import com.school.school.model.ExamRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRatingRepository extends JpaRepository<ExamRating, ExamRatingKey> {
}
