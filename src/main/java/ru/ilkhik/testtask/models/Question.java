package ru.ilkhik.testtask.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@IdClass(QuestionId.class)
public class Question {
    @Id
    private Integer number;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Test test;

    @Enumerated(EnumType.STRING)
    private QuestionKind questionKind;

    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
        for (Answer answer : this.answers) {
            answer.setQuestion(this);
        }
    }
}
