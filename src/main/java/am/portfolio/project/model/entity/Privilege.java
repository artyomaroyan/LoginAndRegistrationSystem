package am.portfolio.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;
import java.util.UUID;

import static org.hibernate.annotations.UuidGenerator.Style.TIME;

/**
 * @author Artyom Aroyan
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue
    @UuidGenerator(style = TIME)
    @Column(name = "privilege_id", nullable = false, unique = true, length = 8)
    private UUID privilegeId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
