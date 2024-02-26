package com.nlp.nlp.model;

import java.util.List;

public class GenerateQuestion {
    String userName;
    String technology;
    List<String> topic;
    String experience;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
