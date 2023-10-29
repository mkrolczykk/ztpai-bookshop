package pl.bookshop.auth.util.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.bookshop.auth.util.entity.UserInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private final String email;

    private final String password;

    @Getter
    private final String name;

    @Getter
    private final String surname;

    private final String userName;

    @Getter
    private final Long id;

    private final List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) {
        email = userInfo.getEmail();
        id = userInfo.getId();
        password = userInfo.getPassword();
        name = userInfo.getName();
        surname = userInfo.getSurname();
        userName = userInfo.getUsername();
        authorities = userInfo.getRoles()
                .stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
