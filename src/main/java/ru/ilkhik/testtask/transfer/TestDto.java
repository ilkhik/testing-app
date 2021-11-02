package ru.ilkhik.testtask.transfer;

import lombok.Builder;
import lombok.Data;
import ru.ilkhik.testtask.models.Test;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TestDto {
    private Integer id;
    private String title;
    private List<QuestionDto> questions;

    public static TestDto from(Test test) {
        return builder()
                .id(test.getId())
                .title(test.getTitle())
                .questions(QuestionDto.from(test.getQuestions()))
                .build();
    }

    public static List<TestDto> from(List<Test> tests) {
        return tests.stream().map(TestDto::from).collect(Collectors.toList());
    }
}
