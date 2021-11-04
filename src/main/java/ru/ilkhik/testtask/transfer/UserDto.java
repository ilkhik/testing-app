package ru.ilkhik.testtask.transfer;

import lombok.Builder;
import lombok.Data;
import ru.ilkhik.testtask.models.Role;
import ru.ilkhik.testtask.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String login;
    private Role role;
    private LocalDate signupDate;

    public static UserDto from(User user) {
        return builder()
                .id(user.getId())
                .login(user.getLogin())
                .role(user.getRole())
                .signupDate(user.getSignupDate())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
