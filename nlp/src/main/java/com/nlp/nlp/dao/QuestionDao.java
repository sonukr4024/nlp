package com.nlp.nlp.dao;

import com.nlp.nlp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDao extends JpaRepository<Question, Long> {

}
