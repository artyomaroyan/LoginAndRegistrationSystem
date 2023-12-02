package am.portfolio.project.service;

import am.portfolio.project.model.request.UserRequest;
import am.portfolio.project.util.PaginationWithSorting;

import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Aroyan
 */

public interface UserService {

    Optional<List<?>> getAllUsersSorted(final PaginationWithSorting paginationWithSorting);

    Optional<?> getUserByUsername(final String username);

    Optional<?> registerUser(final UserRequest request);
}
