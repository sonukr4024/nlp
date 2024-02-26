package com.nlp.nlp.manager;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GrammarCheckManager {
    public List<String> checkGrammar(String text, Language language) {
        JLanguageTool languageTool = new JLanguageTool(language);
        try {
            return languageTool.check(text).stream()
                    .map(ruleMatch -> ruleMatch.getMessage())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
