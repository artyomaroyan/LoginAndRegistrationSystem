package am.portfolio.project.model.entity;

import am.portfolio.project.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static org.hibernate.annotations.UuidGenerator.Style.TIME;

/**
 * @author Artyom Aroyan
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @UuidGenerator(style = TIME)
    @Column(name = "role_id", nullable = false, unique = true, length = 8)
    private UUID roleId;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private RoleEnum name;

    @ManyToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id")
    )
    private Collection<Privilege> privileges;

    public Role(RoleEnum name) {
        this.name = name;
    }
}
