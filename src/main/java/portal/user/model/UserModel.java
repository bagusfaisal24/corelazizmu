package portal.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import portal.core.model.BaseModel;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_user")
@Setter
@Getter
public class UserModel extends BaseModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    private boolean enabled;

    @Column(name = "user_role", nullable = false)
    private String role;

    @Column(name = "account_non_expired", nullable = false)
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean isCredentialsNonExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
}
