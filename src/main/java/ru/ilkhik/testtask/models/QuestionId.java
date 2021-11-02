package ru.ilkhik.testtask.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionId implements Serializable {
    private Integer number;
    private Test test;
}
