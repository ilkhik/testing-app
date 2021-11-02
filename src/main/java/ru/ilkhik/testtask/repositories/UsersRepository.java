package ru.ilkhik.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilkhik.testtask.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByLoginIgnoreCase(String login);
}
