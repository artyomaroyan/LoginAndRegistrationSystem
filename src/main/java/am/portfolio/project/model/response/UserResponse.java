package am.portfolio.project.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Artyom Aroyan
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID userid;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String password;
    private boolean isActive;
    private Collection<RoleResponse> roles;
}
