package am.portfolio.project.controller;

import am.portfolio.project.model.request.UserRequest;
import am.portfolio.project.service.UserService;
import am.portfolio.project.util.PaginationWithSorting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    public ResponseEntity<Optional<List<?>>> getAllUsersSorted(
            @RequestBody final PaginationWithSorting pagination) {

        var result = userService.getAllUsersSorted(pagination);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{username}",
            method = GET,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<?>> getUserByUsername(@PathVariable("username") final String username) {

        var result = userService.getUserByUsername(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/register",
            method = POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<?>> registerUser(@RequestBody final UserRequest request) {

        var result = userService.registerUser(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
