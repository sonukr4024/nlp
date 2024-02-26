package com.nlp.nlp;

import com.nlp.nlp.model.GenerateQuestion;
import com.nlp.nlp.service.CheckUserInputService;
import com.nlp.nlp.service.GenerateQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("")
public class NLPController {

    @Autowired
    GenerateQuestionService generateQuestionService;

    @Autowired
    CheckUserInputService checkAnswerService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("getquestion")
    public String getQuestion(@RequestBody GenerateQuestion generateQuestion) {
        return generateQuestionService.getQuestion(generateQuestion);
    }

    @PostMapping("checkansandgenerateques")
    public String getQuestion(@RequestBody Map<String, Object> userInput) {
        return checkAnswerService.checkUserInput(userInput);
    }
}
