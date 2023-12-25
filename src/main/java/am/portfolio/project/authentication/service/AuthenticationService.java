package am.portfolio.project.authentication.service;

import am.portfolio.project.authentication.dto.AuthenticationRequest;
import am.portfolio.project.authentication.dto.AuthenticationResponse;
import am.portfolio.project.authentication.jwt.JWTService;
import am.portfolio.project.enums.RoleEnum;
import am.portfolio.project.model.entity.Role;
import am.portfolio.project.model.entity.User;
import am.portfolio.project.model.request.RegisterRequest;
import am.portfolio.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Artyom Aroyan
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Optional<AuthenticationResponse> register(RegisterRequest request) {

        var defaultRole = new ArrayList<>(convertToRoleFromEnum());

        var user = User.builder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .roles(defaultRole)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return Optional.ofNullable(AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build());
    }

    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return Optional.ofNullable(AuthenticationResponse.builder()
                .accessToken(jwt)
                .build());
    }

    private Collection<Role> convertToRoleFromEnum() {
        return Collections.singleton(new Role(RoleEnum.USER));
    }
}
