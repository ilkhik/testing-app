package ru.ilkhik.testtask.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 32)
    private String title;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    private Integer maxScores;

    public void computeMaxScores() {
        maxScores = 0;
        questions.forEach(q -> maxScores += q.getQuestionKind()
                .equals(QuestionKind.SINGLE_CHOICE) ? 1 : 3);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        for (Question question : this.questions) {
            question.setTest(this);
        }

    }

    public Question getQuestionByNumber(int number) {
        return getQuestions().get(number - 1);
    }

    public boolean checkQuestion(int questionNumber, Set<Integer> answers) {
        Set<Integer> correctAnswers = new HashSet<>();
        getQuestionByNumber(questionNumber).getAnswers().forEach((a) -> {
            if (a.getIsCorrect())
                correctAnswers.add(a.getNumber());
        });
        return answers.equals(correctAnswers);
    }
}
