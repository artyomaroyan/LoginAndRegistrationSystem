package am.portfolio.project.model.request;

import lombok.Data;

/**
 * @author Artyom Aroyan
 */
@Data
public class ChangePassword {

    private String oldPassword;
    private String newPassword;
}
