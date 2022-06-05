package ru.ilkhik.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilkhik.testtask.forms.QuestionTakeForm;
import ru.ilkhik.testtask.models.*;
import ru.ilkhik.testtask.forms.TestTakeForm;
import ru.ilkhik.testtask.repositories.TakedTestsRepository;
import ru.ilkhik.testtask.repositories.TestsRepository;
import ru.ilkhik.testtask.repositories.UsersRepository;
import ru.ilkhik.testtask.services.exceptions.TestAlreadyExistsException;
import ru.ilkhik.testtask.transfer.TestDto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestsRepository testsRepository;

    @Autowired
    private TakedTestsRepository takedTestsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<TestDto> getAllTests() {
        return TestDto.from(testsRepository.findAll());
    }

    public void createNewTest(Test test) throws TestAlreadyExistsException {
        List<Question> questions = test.getQuestions();
        int i = 1;
        for (Question question : questions) {
            question.setNumber(i);
            List<Answer> answers = question.getAnswers();
            int j = 1;
            for (Answer answer : answers) {
                answer.setNumber(j);
                ++j;
            }
            ++i;
        }
        test.computeMaxScores();
        try {
            testsRepository.save(test);
        } catch (Throwable t) {
            throw new TestAlreadyExistsException();
        }
    }

    public void takeTest(int testId, User user, TestTakeForm testTakeForm) {
        Test test = testsRepository.findById(testId).orElseThrow(IllegalArgumentException::new);

        int score = 0;
        List<QuestionTakeForm> questions = testTakeForm.getQuestions();
        for (QuestionTakeForm question : questions) {
            if (test.checkQuestion(question.getNumber(), new HashSet<>(question.getAnswers()))) {
                score += test.getQuestionByNumber(question.getNumber())
                        .getQuestionKind().equals(QuestionKind.SINGLE_CHOICE) ? 1 : 3;
            }
        }
        TakedTest takedTest = TakedTest.builder()
                .score(score)
                .test(test)
                .user(user)
                .build();

        user.setScoreMaxSum(user.getScoreMaxSum()+test.getMaxScores());
        user.setScoreSum(user.getScoreSum()+score);
        user.setTestPassedNumber(user.getTestPassedNumber()+1);
        user.getTakedTests().add(takedTest);
        usersRepository.save(user);
        takedTestsRepository.save(takedTest);
    }

    public TestDto findById(int id) {
        TestDto dto = TestDto.from(testsRepository.findById(id).orElseThrow(IllegalArgumentException::new));
        dto.getQuestions().forEach(q -> Collections.shuffle(q.getAnswers()));
        Collections.shuffle(dto.getQuestions());
        return dto;
    }

    public Optional<TakedTest> getTakedTest(User user, int testId) {
        return takedTestsRepository.findByUserAndTest(user, testsRepository.findById(testId)
                    .orElseThrow(IllegalArgumentException::new));
    }
}
