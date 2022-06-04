package ru.ilkhik.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilkhik.testtask.models.Role;
import ru.ilkhik.testtask.models.User;
import ru.ilkhik.testtask.repositories.UsersRepository;
import ru.ilkhik.testtask.transfer.UserDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public List<UserDto> findAll() {
        return UserDto.from(usersRepository.findAll());
    }

    public void changeRole(int id, boolean admin) {
        User user = usersRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.setRole(admin ? Role.ADMIN : Role.USER);
        usersRepository.save(user);
    }

    public Optional<User> getUserByLogin(String login) {
        return usersRepository.findOneByLoginIgnoreCase(login);
    }
}
