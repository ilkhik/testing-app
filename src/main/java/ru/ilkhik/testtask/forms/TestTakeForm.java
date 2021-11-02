package ru.ilkhik.testtask.forms;

import lombok.Data;

import java.util.List;

@Data
public class TestTakeForm {
    private List<QuestionTakeForm> questions;
}
