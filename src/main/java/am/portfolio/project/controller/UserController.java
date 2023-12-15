package am.portfolio.project.controller;

import am.portfolio.project.model.request.RegisterRequest;
import am.portfolio.project.model.response.UserResponse;
import am.portfolio.project.service.UserService;
import am.portfolio.project.util.PaginationWithSorting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Artyom Aroyan
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @RequestMapping(
            value = "/sort",
            method = GET,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Optional<List<UserResponse>>> getAllUsersSorted(
            @RequestBody final PaginationWithSorting pagination) {

        var result = userService.getAllUsersSorted(pagination);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/username/{username}",
            method = GET,
            produces = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Optional<?>> getUserByUsername(
            @PathVariable("username") final String username) {

        var result = userService.getUserByUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/register",
            method = POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<?>> register(@RequestBody final RegisterRequest request) {

        var result = userService.register(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/update/{id}",
            method = PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('USER_ROLE')")
    public ResponseEntity<Optional<?>> updateUser(
            @PathVariable("id") final UUID userId,
            @RequestBody final RegisterRequest request) {

        var result = userService.updateUser(userId, request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
