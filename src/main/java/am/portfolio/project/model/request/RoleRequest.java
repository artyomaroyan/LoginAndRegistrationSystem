package am.portfolio.project.model.request;

import am.portfolio.project.enums.RoleEnum;
import lombok.*;

/**
 * @author Artyom Aroyan
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    private RoleEnum name;
}
