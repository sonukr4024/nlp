package com.nlp.nlp.manager;

import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CheckedUserInputManager {
    @Autowired
    GrammarCheckManager grammarCheckManager;

    public String checkUserInput(Map<String, Object> inputText) {
        Map<Long, List<String>> longListMap = staticKeywords();
        String[] tokens = SimpleTokenizer.INSTANCE.tokenize(inputText.get("inputText").toString());
        List<String> keyWords = longListMap.getOrDefault(inputText.get("questionId"), new ArrayList<>());
        int matchingKeywordCount = 0;
        for (String keyword : keyWords) {
            for (String token : tokens) {
                if (token.equals(keyword)) {
                    matchingKeywordCount++;
                    break;
                }
            }
        }
        return matchingKeywordCount > keyWords.size() / 2 ? "good" : "bak";
    }

    private Map<Long, List<String>> staticKeywords() {
        Map<Long, List<String>> keywordMap = new HashMap<>();
        keywordMap.put(1L, Arrays.asList("compares", "object reference", "memory", "location", "values", "content"));
        keywordMap.put(2L, Arrays.asList("method overloading", "multiple methods", "same name", "different parameters", "compiler", "call"));
        keywordMap.put(3L, Arrays.asList("transient", "keyword", "variable", "serialized", "object", "byte stream"));
        keywordMap.put(4L, Arrays.asList("StringBuilder", "StringBuffer", "mutable strings", "thread-safe", "methods", "synchronized"));
        keywordMap.put(5L, Arrays.asList("Java", "multiple inheritances", "interfaces", "class", "implement", "abstract methods"));
        keywordMap.put(6L, Arrays.asList("volatile", "keyword", "variable's value", "changed", "multiple threads", "simultaneously"));
        keywordMap.put(7L, Arrays.asList("autoboxing", "unboxing", "automatic conversion", "primitive data types", "wrapper classes", "Integer"));
        keywordMap.put(8L, Arrays.asList("super", "keyword", "immediate parent class", "invoke", "access", "constructor"));
        keywordMap.put(9L, Arrays.asList("Singleton pattern", "Java", "private constructor", "static method", "instance", "created"));
        keywordMap.put(10L, Arrays.asList("ArrayList", "LinkedList", "backed by", "array", "doubly-linked list", "random access", "insertions", "deletions"));
        return keywordMap;
    }
}
