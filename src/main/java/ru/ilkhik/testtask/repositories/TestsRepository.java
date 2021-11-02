package ru.ilkhik.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilkhik.testtask.models.Test;

public interface TestsRepository extends JpaRepository<Test, Integer> {
}
