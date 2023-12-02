package am.portfolio.project.mapper;

import am.portfolio.project.model.entity.User;
import am.portfolio.project.model.request.UserRequest;
import am.portfolio.project.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

/**
 * @author Artyom Aroyan
 */

@Component
public class UserMapper {

    private final ModelMapper mapper;

    public UserMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setMatchingStrategy(STRICT);
    }

    public UserResponse mapFromResponseToEntity(Optional<User> user) {
        return mapper.map(user, UserResponse.class);
    }

    public User mapFromEntityToRequest(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserResponse mapFromResponseToEntity(User user) {

        return mapper.map(user, UserResponse.class);
    }
}