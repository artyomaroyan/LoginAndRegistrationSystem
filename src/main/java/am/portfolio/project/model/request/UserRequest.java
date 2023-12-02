package am.portfolio.project.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

/**
 * @author Artyom Aroyan
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty(message = "The username must not be empty")
    @Size(min = 3, max = 25, message = "The username must be between 3 and 15 characters long")
    private String username;

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, max = 15, message = "First name must be between 2 and 10 characters long")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, max = 15, message = "Last name must be between 2 and 10 characters long")
    private String lastName;

    @Email
    @NotEmpty(message = "The email must not be empty")
    @Size(min = 5, max = 35, message = "The email must be between 5 and 25 characters long")
    private String email;

    @NotEmpty(message = "The user age must not be empty")
    @Min(value = 18, message = "The user must be bigger than 18 year old")
    private int age;

    @NotEmpty(message = "The password must not be empty")
    private String password;

    private Collection<RoleRequest> roles;
}
