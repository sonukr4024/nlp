package com.nlp.nlp.service;

import com.nlp.nlp.manager.CheckedUserInputManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckUserInputServiceImpl implements CheckUserInputService {
    @Autowired
    CheckedUserInputManager checkedUserInputManager;

    @Override
    public String checkUserInput(Map<String, Object> inputText) {
        return checkedUserInputManager.checkUserInput(inputText);
    }
}
