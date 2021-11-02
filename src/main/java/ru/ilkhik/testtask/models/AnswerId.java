package ru.ilkhik.testtask.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerId implements Serializable {
    private Integer number;
    private Question question;
}
