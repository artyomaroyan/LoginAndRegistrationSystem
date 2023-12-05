package am.portfolio.project.model.response;

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

    private UUID roelId;
    private String name;
}
