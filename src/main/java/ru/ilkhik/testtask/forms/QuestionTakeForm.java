package ru.ilkhik.testtask.forms;

import lombok.Data;

import java.util.List;

@Data
public class QuestionTakeForm {
    private Integer number;
    private List<Integer> answers;
}
