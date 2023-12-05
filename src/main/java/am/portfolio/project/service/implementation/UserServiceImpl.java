package am.portfolio.project.service.implementation;

import am.portfolio.project.mapper.UserMapper;
import am.portfolio.project.model.request.UserRequest;
import am.portfolio.project.repository.UserRepository;
import am.portfolio.project.service.UserService;
import am.portfolio.project.util.PaginationWithSorting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Aroyan
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<List<?>> getAllUsersSorted(PaginationWithSorting paginationWithSorting) {
        PageRequest pageRequest = PageRequest.of(
                paginationWithSorting.getPageSize(),
                paginationWithSorting.getContentsNumber(),
                Sort.Direction.valueOf(paginationWithSorting.getSortDirection()),
                paginationWithSorting.getSortElement());

        var result = userRepository.findAll(pageRequest).getContent();
        return Optional.of(result);
    }

    @Override
    public Optional<?> getUserByUsername(String username) {

        var user = userRepository.findUserByUsername(username);

        if (user.isEmpty()) {
            log.error("User by username " + username + " does not exist:");
        }
        var response = userMapper.mapFromResponseToEntity(user);
        return Optional.ofNullable(response);
    }

    @Override
    @Transactional
    public Optional<?> registerUser(UserRequest request) {

        var checkIfUserExists = userRepository.existsByEmail(request.getEmail());

        if (checkIfUserExists) {
            log.error(request.getEmail() + " This email already has taken:");
        }

        var register = userMapper.mapFromEntityToRequest(request);
        userRepository.save(register);
        var response  = userMapper.mapFromResponseToEntity(register);
        return Optional.ofNullable(response);
    }
}
