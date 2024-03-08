package com.nlp.nlp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String question;
    String technology;
    String topic;
    String level;
    String importance;
    String experienceRange;

    long createdDt;
    long updatedDt;
    long createdBy;
    long updatedBy;
    public Question() {
        // Initialization code if needed
    }

    public Question(String question, String technology, String topic, String level, String importance, String experienceRange) {
        this.question = question;
        this.technology = technology;
        this.topic = topic;
        this.level = level;
        this.importance = importance;
        this.experienceRange = experienceRange;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getExperienceRange() {
        return experienceRange;
    }

    public void setExperienceRange(String experienceRange) {
        this.experienceRange = experienceRange;
    }

    public long getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(long createdDt) {
        this.createdDt = createdDt;
    }

    public long getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(long updatedDt) {
        this.updatedDt = updatedDt;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }
}
