package ru.ilkhik.testtask.transfer;

import lombok.Builder;
import lombok.Data;
import ru.ilkhik.testtask.models.Question;
import ru.ilkhik.testtask.models.QuestionKind;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class QuestionDto {
    private Integer number;
    private String text;
    private QuestionKind questionKind;
    private List<AnswerDto> answers;

    public static QuestionDto from(Question question) {
        return builder()
                .number(question.getNumber())
                .text(question.getText())
                .questionKind(question.getQuestionKind())
                .answers(AnswerDto.from(question.getAnswers()))
                .build();
    }

    public static List<QuestionDto> from(List<Question> questions) {
        return questions.stream().map(QuestionDto::from).collect(Collectors.toList());
    }
}
