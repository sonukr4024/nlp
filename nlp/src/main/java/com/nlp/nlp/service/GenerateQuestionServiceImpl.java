package com.nlp.nlp.service;

import com.nlp.nlp.manager.GenerateQuestionManager;
import com.nlp.nlp.model.GenerateQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateQuestionServiceImpl implements GenerateQuestionService {
    @Autowired
    GenerateQuestionManager generateQuestionManager;

    @Override
    public String getQuestion(GenerateQuestion generateQuestion) {
        return generateQuestionManager.getQuestion(generateQuestion);
    }
}
