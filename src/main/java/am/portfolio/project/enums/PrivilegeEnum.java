package am.portfolio.project.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Artyom Aroyan
 */

@Getter
@RequiredArgsConstructor
public enum PrivilegeEnum {

    VIEW("user:view"),
    READ("user:read"),
    CREATE("admin:create"),
    UPDATE("user:update"),
    CHANGE("admin:change"),
    DELETE("admin:delete");

    private final String privileges;
}
