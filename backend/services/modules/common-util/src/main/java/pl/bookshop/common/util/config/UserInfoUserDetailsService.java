package pl.bookshop.common.util.config;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.bookshop.common.util.entity.UserInfo;
import pl.bookshop.common.util.service.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    private final UserInfoRepository repository;

    public UserInfoUserDetailsService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserInfo> userInfo = repository.findByEmail(email);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new InternalAuthenticationServiceException("Email not found"));

    }
}
