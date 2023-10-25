package pl.bookshop.common.util.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@FieldNameConstants
@NoArgsConstructor
@Table(name = "system_user")
@Entity(name = "system_user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = Fields.name)
    private String name;

    @Column(name = Fields.surname)
    private String surname;

    @Column(name = Fields.username)
    private String username;

    @Column(name = Fields.password)
    private String password;

    @Column(name = Fields.email)
    private String email;

    @Column(name = "email_accepted")
    private Boolean emailAccepted;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_intro")
    private String userIntro;

    @Column(name = "user_descr")
    private String userDescr;

    @Column(name = "notifications")
    private Boolean notifications;

    @Column(name = "registered_at")
    @CreationTimestamp
    private Timestamp registeredAt;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public Set<RolesEnum> getRoles() {
        return roles.stream()
                .map(RoleEntity::getName)
                .map(RolesEnum::valueOf)
                .collect(Collectors.toSet());
    }

    public void setRoles(Set<RolesEnum> roles) {
        this.roles = roles.stream()
                .map(role -> new RoleEntity(role.getIdentity(), role.name()))
                .collect(Collectors.toSet());
    }

    public void setDefaultRole() {
        setRoles(Set.of(RolesEnum.ROLE_USER));
    }
}
