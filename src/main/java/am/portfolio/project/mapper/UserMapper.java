package am.portfolio.project.mapper;

import am.portfolio.project.enums.RoleEnum;
import am.portfolio.project.model.entity.Role;
import am.portfolio.project.model.entity.User;
import am.portfolio.project.model.request.RegisterRequest;
import am.portfolio.project.model.response.RoleResponse;
import am.portfolio.project.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

/**
 * @author Artyom Aroyan
 */

@Component
public class UserMapper {

    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setMatchingStrategy(STRICT);
        this.passwordEncoder = new BCryptPasswordEncoder(11);
    }

    public UserResponse mapFromEntityToResponse(Optional<User> user) {
        return mapper.map(user, UserResponse.class);
    }

    public User mapFromRequestToEntity(RegisterRequest request) {
        return BuildUser(request);
    }

    public UserResponse mapFromEntityToResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }

    public Optional<List<UserResponse>> mapFromEntityListToResponseList(List<User> users) {

        var defaultRole = new ArrayList<>(convertToRoleResponseFromEnum());

        return Optional.of(users.stream()
                .map(response -> UserResponse.builder()
                        .userid(response.getUserid())
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .username(response.getUsername())
                        .firstName(response.getFirstName())
                        .lastName(response.getLastName())
                        .email(response.getEmail())
                        .age(response.getAge())
                        .password(passwordEncoder.encode(response.getPassword()))
                        .isActive(true)
                        .roles(defaultRole)
                        .build())
                .collect(Collectors.toList()));
    }


    private User BuildUser(RegisterRequest request) {

        var defaultRole = new ArrayList<>(convertToRoleFromEnum());
        var password = passwordEncoder.encode(request.getPassword());

        return User.builder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(password)
                .isActive(true)
                .roles(defaultRole)
                .build();
    }

    private Collection<Role> convertToRoleFromEnum() {
        return Collections.singleton(new Role(RoleEnum.USER));
    }

    private Collection<RoleResponse> convertToRoleResponseFromEnum() {
        return Collections.singleton(new RoleResponse(RoleEnum.USER));
    }
}