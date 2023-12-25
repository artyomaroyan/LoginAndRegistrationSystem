package am.portfolio.project.service.implementation;

import am.portfolio.project.mapper.UserMapper;
import am.portfolio.project.model.entity.Role;
import am.portfolio.project.model.request.ChangePassword;
import am.portfolio.project.model.request.RoleRequest;
import am.portfolio.project.model.request.RegisterRequest;
import am.portfolio.project.model.response.UserResponse;
import am.portfolio.project.repository.UserRepository;
import am.portfolio.project.service.UserService;
import am.portfolio.project.util.PaginationWithSorting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Artyom Aroyan
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * Retrieves all users, sorted and paginated
     *
     * @param paginationWithSorting Options for sorting and pagination
     * @return An optional containing a list of sorted user data
     */
    @Override
    public Optional<List<UserResponse>> getAllUsersSorted(PaginationWithSorting paginationWithSorting) {
        PageRequest pageRequest = PageRequest.of(
                paginationWithSorting.getPageSize(),
                paginationWithSorting.getContentsNumber(),
                Sort.Direction.valueOf(paginationWithSorting.getSortDirection()),
                paginationWithSorting.getSortElement());

        var response = userRepository.findAll(pageRequest).getContent();

        return userMapper.mapFromEntityListToResponseList(response);
    }

    /**
     * Retrieves a user by username
     *
     * @param username The username of the user to find
     * @return An optional containing user data identified by the username
     */
    // TODO: Replace the current error logging with a UsernameNotFoundException for better error handling
    @Override
    public Optional<?> getUserByUsername(String username) {

        var user = userRepository.findUserByUsername(username);

        if (user.isEmpty()) {
            log.error("User by username " + username + " does not exist:");
        }
        var response = userMapper.mapFromEntityToResponse(user);
        return Optional.ofNullable(response);
    }

    /**
     * Register a new user
     *
     * @param request The user request object
     * @return An optional containing the user response with the created user
     */
    // TODO: Replace the current error logging with a EmailAlreadyTaken exception for better error handling
    @Override
    @Transactional
    public Optional<?> register(RegisterRequest request) {

        var existsByEmail = userRepository.existsByEmail(request.getEmail());

        if (existsByEmail) {
            log.error(request.getEmail() + " This email already has taken:");
        }

        var register = userMapper.mapFromRequestToEntity(request);
        userRepository.save(register);
        var response  = userMapper.mapFromEntityToResponse(register);
        return Optional.ofNullable(response);
    }

    /**
     * Update an existing user
     *
     * @param userId The ID of the user to check for existence
     * @param request The user request object
     * @return An optional containing the user information after the update
     */
    @Override
    @Transactional
    public Optional<?> updateUser(UUID userId, RegisterRequest request) {

        var existsById = userRepository.existsById(userId);

        if (!existsById) {
            log.error("No User with the specified ID exists:");
        }

        var update = userMapper.mapFromRequestToEntity(request);

        update.setUpdateDate(LocalDateTime.now());
        update.setUsername(request.getUsername());
        update.setFirstName(request.getFirstName());
        update.setLastName(request.getLastName());
        update.setEmail(request.getEmail());
        update.setAge(request.getAge());
        // TODO:
        //  Reevaluate the necessity of this line when implementing changePassword and resetPassword methods,
        //  @Mentore please review and let me know delete or leave it
        update.setPassword(request.getPassword());
        update.setRoles(convertToRole(request.getRoles()));

        userRepository.save(update);
        var response = userMapper.mapFromEntityToResponse(update);

        return Optional.ofNullable(response);
    }

    @Override
    public Optional<?> changePassword(UUID userId, ChangePassword request) {
        return Optional.empty();
    }

    /**
     * Converts a collection of RoleRequest objects to a collection of RoleEnum entities
     *
     * @param requests The collection of RoleRequest objects to be converted
     * @return A collection of RoleEnum entities created from the RoleRequest objects
     */
    private Collection<Role> convertToRole(Collection<RoleRequest> requests) {
        return requests.stream()
                .map(role -> new Role(role.getName()))
                .collect(Collectors.toList());
    }
}
