package pl.bookshop.auth.util.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.bookshop.auth.util.config.UserInfoUserDetails;

@UtilityClass
public class AuthenticationUtils {

    public String getUserEmail() {
        return ((UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Long getUserId() {
        return ((UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
