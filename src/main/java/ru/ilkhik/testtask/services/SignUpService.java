package ru.ilkhik.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ilkhik.testtask.forms.UserForm;
import ru.ilkhik.testtask.models.Role;
import ru.ilkhik.testtask.models.User;
import ru.ilkhik.testtask.repositories.UsersRepository;
import ru.ilkhik.testtask.services.exceptions.UserAlreadyExistsException;

import java.time.LocalDateTime;

@Service
public class SignUpService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    public void signUp(UserForm userForm) throws UserAlreadyExistsException {
        String passwordHash = passwordEncoder.encode(userForm.getPassword());
        User user = new User();
        user.setLogin(userForm.getLogin());
        user.setPasswordHash(passwordHash);
        user.setRole(Role.USER);
        user.setSignupDate(LocalDateTime.now().toLocalDate());
        user.setTestPassedNumber(0);
        user.setScoreSum(0);
        user.setScoreMaxSum(0);

        try {
            usersRepository.save(user);
        } catch (Throwable t) {
            throw new UserAlreadyExistsException();
        }
    }
}
