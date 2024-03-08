package com.nlp.nlp.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class LoginInput {
    String env;
    String product;
    String project;
    String emailId;
    long offset;
}
