package com.mungta.questionsservice.domain.question.repository;

import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByIdAndDisplayStatus(Long id, DisplayStatus displayStatus);
    List<Question> findAllByUserIdAndDisplayStatusOrderByCreatedDateDesc(Long userId, DisplayStatus displayStatus);
    List<Question> findAllByDisplayStatusOrderByCreatedDateDesc(DisplayStatus displayStatus);
}
