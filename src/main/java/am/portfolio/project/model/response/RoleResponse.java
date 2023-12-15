package am.portfolio.project.model.response;

import am.portfolio.project.enums.RoleEnum;
import lombok.*;

import java.util.UUID;

/**
 * @author Artyom Aroyan
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    private UUID roleId;
    private RoleEnum name;

    public RoleResponse(RoleEnum name) {
        this.name = name;
    }
}
