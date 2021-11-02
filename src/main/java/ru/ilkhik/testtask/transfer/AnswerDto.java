package ru.ilkhik.testtask.transfer;

import lombok.Builder;
import lombok.Data;
import ru.ilkhik.testtask.models.Answer;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AnswerDto {
    private Integer number;
    private String text;

    public static AnswerDto from(Answer answer) {
        return builder()
                .number(answer.getNumber())
                .text(answer.getText())
                .build();
    }

    public static List<AnswerDto> from(List<Answer> answers) {
        return answers.stream().map(AnswerDto::from).collect(Collectors.toList());
    }
}
