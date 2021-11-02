package ru.ilkhik.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilkhik.testtask.models.TakedTest;
import ru.ilkhik.testtask.models.Test;
import ru.ilkhik.testtask.models.User;

import java.util.Optional;

public interface TakedTestsRepository extends JpaRepository<TakedTest, Integer> {
    Optional<TakedTest> findByUserAndTest(User user, Test test);
}
