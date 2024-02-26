package com.nlp.nlp.manager;

import com.nlp.nlp.dao.QuestionDao;
import com.nlp.nlp.model.GenerateQuestion;
import com.nlp.nlp.model.Question;
import com.nlp.nlp.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenerateQuestionManager {
    @Autowired
    QuestionDao questionDao;

    public String getQuestion(GenerateQuestion generateQuestion) {
//        List<Question> questions = questionDao.saveAll(getStaticQuestionList());
        List<Question> questionList = getQuestions(generateQuestion);
        String question = generateQuestionByUserResponse(questionList, null);
        return question;
    }

    public String generateQuestionByUserResponse(List<Question> questionList, String userResponse) {
        Question question = null;
        if (userResponse == null) {
            question = questionList.stream().filter(f -> f.getLevel().equalsIgnoreCase("basic")).findFirst().orElse(null);
        } else {
            question = askMatchQuestionOrGetRandomQuestion();
        }
        if (question != null) {
            List<Question> questions = (List<Question>) CacheUtils.getValueFormCache("questionList");
            questions.remove(question);
            CacheUtils.setValueInCache("questionList", questions);
            return question.getQuestion();
        }
        return "";
    }

    private Question askMatchQuestionOrGetRandomQuestion() {
        return null;
    }

    private List<Question> getQuestions(GenerateQuestion generateQuestion) {
        if (!CacheUtils.containsKeyInCache("questionList")) {
            List<Question> questionList = getStaticQuestionList();
            List<Question> questions = questionList.stream().filter(question -> question.getTechnology().equalsIgnoreCase(generateQuestion.getTechnology())).collect(Collectors.toList());
            CacheUtils.setValueInCache("questionList", questions);
        }
        List<Question> questionList = (List<Question>) CacheUtils.getValueFormCache("questionList");
        return questionList;
    }


    private List<Question> getStaticQuestionList() {

        List<Question> questionList = new ArrayList<>();

        questionList.add(new Question(1, "What is the difference between == and .equals() in Java?", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        questionList.add(new Question(2, "Explain the concept of method overloading in Java.", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(3, "What is the purpose of the transient keyword in Java?", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(4, "Describe the difference between StringBuilder and StringBuffer.", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(5, "How does Java support multiple inheritances through interfaces?", "Java", "Object-Oriented Programming (OOP)", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(6, "What is the purpose of the volatile keyword in Java?", "Java", "Multi-Threading", "Advanced", "Intermediate", "5+ years"));

        questionList.add(new Question(7, "Explain the concept of autoboxing and unboxing in Java.", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        questionList.add(new Question(8, "What is the super keyword used for in Java?", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        questionList.add(new Question(9, "How can you achieve a Singleton pattern in Java?", "Java", "Design Patterns", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(10, "Describe the differences between ArrayList and LinkedList.", "Java", "Java Collections Framework", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(11, "Explain the concept of anonymous classes in Java.", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(12, "What is the significance of the @Override annotation?", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 13
        questionList.add(new Question(13, "How does the static block differ from the static method in Java?", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        // Question 14
        questionList.add(new Question(14, "What is the purpose of the Comparable interface?", "Java", "Java Collections Framework", "Intermediate", "Intermediate", "2-5 years"));

        // Question 15
        questionList.add(new Question(15, "Explain the difference between String and StringBuffer in terms of mutability.", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 16
        questionList.add(new Question(16, "Describe the Observer design pattern in Java.", "Java", "Design Patterns", "Intermediate", "Intermediate", "2-5 years"));

        // Question 17
        questionList.add(new Question(17, "What is the purpose of the final class in Java?", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        // Question 18
        questionList.add(new Question(18, "Explain the concept of method overriding in Java.", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 19
        questionList.add(new Question(19, "How can you handle exceptions in a multi-threaded environment?", "Java", "Multi-Threading", "Advanced", "Intermediate", "5+ years"));

        // Question 20
        questionList.add(new Question(20, "Describe the principles of Dependency Injection (DI) in Java.", "Java", "Design Patterns", "Intermediate", "Intermediate", "2-5 years"));

        questionList.add(new Question(21, "What is the purpose of the 'finally' block in exception handling?", "Java", "Exception Handling", "Intermediate", "Intermediate", "2-5 years"));

        // Question 22
        questionList.add(new Question(22, "Explain the concept of lambda expressions in Java.", "Java", "Java 8 Features", "Intermediate", "Intermediate", "2-5 years"));

        // Question 23
        questionList.add(new Question(23, "What is the difference between '==' and 'equals()' for comparing strings?", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 24
        questionList.add(new Question(24, "Describe the purpose of the 'break' and 'continue' statements in Java.", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 25
        questionList.add(new Question(25, "How does garbage collection work in Java?", "Java", "Memory Management", "Intermediate", "Intermediate", "2-5 years"));

        // Question 26
        questionList.add(new Question(26, "What is the purpose of the 'this' keyword in Java?", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 27
        questionList.add(new Question(27, "Explain the concept of the 'try-with-resources' statement.", "Java", "Exception Handling", "Intermediate", "Intermediate", "2-5 years"));

        // Question 28
        questionList.add(new Question(28, "What is the difference between method references and lambda expressions?", "Java", "Java 8 Features", "Intermediate", "Intermediate", "2-5 years"));

        // Question 29
        questionList.add(new Question(29, "Describe the purpose of the 'assert' statement in Java.", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        // Question 30
        questionList.add(new Question(30, "How does the 'ClassLoader' work in Java?", "Java", "Class Loading", "Intermediate", "Intermediate", "2-5 years"));

        // Question 31
        questionList.add(new Question(31, "Explain the concept of the 'Decorator' design pattern.", "Java", "Design Patterns", "Intermediate", "Intermediate", "2-5 years"));

        // Question 32
        questionList.add(new Question(32, "What is the purpose of the 'java.util.function' package in Java?", "Java", "Java 8 Features", "Intermediate", "Intermediate", "2-5 years"));

        // Question 33
        questionList.add(new Question(33, "Describe the 'try-with-resources' statement in Java.", "Java", "Exception Handling", "Intermediate", "Intermediate", "2-5 years"));

        // Question 34
        questionList.add(new Question(34, "How does the 'HashMap' handle collisions?", "Java", "Java Collections Framework", "Intermediate", "Intermediate", "2-5 years"));

        // Question 35
        questionList.add(new Question(35, "What is the purpose of the 'java.lang.Math' class in Java?", "Java", "Core Java Concepts", "Basic", "Intermediate", "2-5 years"));

        // Question 36
        questionList.add(new Question(36, "Explain the concept of the 'try-catch-finally' block in Java.", "Java", "Exception Handling", "Intermediate", "Intermediate", "2-5 years"));

        // Question 37
        questionList.add(new Question(37, "Describe the 'Proxy' design pattern in Java.", "Java", "Design Patterns", "Intermediate", "Intermediate", "2-5 years"));

        // Question 38
        questionList.add(new Question(38, "What is the purpose of the 'java.util.stream' package in Java?", "Java", "Java 8 Features", "Intermediate", "Intermediate", "2-5 years"));

        // Question 39
        questionList.add(new Question(39, "Explain the concept of 'covariant return types' in Java.", "Java", "Core Java Concepts", "Intermediate", "Intermediate", "2-5 years"));

        // Question 40
        questionList.add(new Question(40, "How does the 'CopyOnWriteArrayList' work in Java?", "Java", "Java Collections Framework", "Intermediate", "Intermediate", "2-5 years"));

        return questionList;
    }
}
