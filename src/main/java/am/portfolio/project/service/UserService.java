package am.portfolio.project.service;

import am.portfolio.project.model.request.ChangePassword;
import am.portfolio.project.model.request.RegisterRequest;
import am.portfolio.project.model.response.UserResponse;
import am.portfolio.project.util.PaginationWithSorting;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artyom Aroyan
 */

public interface UserService {

    Optional<List<UserResponse>> getAllUsersSorted(final PaginationWithSorting paginationWithSorting);

    Optional<?> getUserByUsername(final String username);

    Optional<?> register(final RegisterRequest request);

    Optional<?> updateUser(final UUID userId, final RegisterRequest request);

    Optional<?> changePassword(final UUID userId, final ChangePassword request);

}
