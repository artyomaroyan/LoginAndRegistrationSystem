package am.portfolio.project.authentication.controller;

import am.portfolio.project.authentication.dto.AuthenticationRequest;
import am.portfolio.project.authentication.dto.AuthenticationResponse;
import am.portfolio.project.authentication.service.AuthenticationService;
import am.portfolio.project.model.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Artyom Aroyan
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    public ResponseEntity<Optional<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
        if (request != null &&
                StringUtils.isNotBlank(request.getUsername()) &&
                StringUtils.isNotBlank(request.getPassword())) {
            return ResponseEntity.ok(authenticationService.register(request));
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(
            value = "/token",
            method = RequestMethod.POST
    )
    public ResponseEntity<Optional<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {

        if (request != null &&
                StringUtils.isNotBlank(request.getUsername()) &&
                StringUtils.isNotBlank(request.getPassword())) {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
        return ResponseEntity.badRequest().build();
    }
}
