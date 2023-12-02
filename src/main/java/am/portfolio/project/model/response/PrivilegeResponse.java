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
public class PrivilegeResponse {

    private UUID privilegeId;
    private String name;

}
