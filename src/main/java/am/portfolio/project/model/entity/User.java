package am.portfolio.project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator(style = TIME)
    @Column(name = "user_id", nullable = false, unique = true, length = 8)
    private UUID userid;

    @CreationTimestamp
    private LocalDateTime crateDate;
    @CreationTimestamp
    private LocalDateTime updateDate;

    @Column(name = "username", nullable = false, unique = true, length = 25)
    private String username;

    @Column(name = "firs_name", nullable = false, length = 15)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 15)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 35)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean isActive;

    @ManyToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private Collection<Role> roles;
}
