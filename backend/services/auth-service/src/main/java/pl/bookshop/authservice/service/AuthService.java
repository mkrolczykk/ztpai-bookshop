package pl.bookshop.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.bookshop.common.util.entity.UserInfo;
import pl.bookshop.common.util.exception.dto.ValidationErrorList;
import pl.bookshop.common.util.messages.MessagesEnum;
import pl.bookshop.common.util.service.repository.UserInfoRepository;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserInfoRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfo registerUser(UserInfo userInfo) {

        validateRegisterRequest(userInfo);

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setDefaultRole();
        userInfo.setEmailAccepted(Boolean.TRUE);    // TODO -> temporary workaround
        userInfo.setNotifications(Boolean.TRUE);

        return repository.save(userInfo);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void validateRegisterRequest(UserInfo userInfo) {

        ValidationErrorList validationErrorList = ValidationErrorList.empty();

        validateField(
                userInfo.getEmail(), UserInfo.Fields.email, this::existsByEmail, MessagesEnum.EXISTS_EMAIL, validationErrorList);
        validateField(
                userInfo.getUsername(), UserInfo.Fields.username, this::existsByUsername, MessagesEnum.EXISTS_USER_NAME, validationErrorList);

        validationErrorList.throwIfNotEmpty();
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean existsByUsername(String userName) {
        return repository.existsByUsername(userName);
    }

    private void validateField(String value, String fieldName, Predicate<String> validationFunction, MessagesEnum message, ValidationErrorList errorList) {
        if (validationFunction.test(value)) {
            errorList.add(fieldName, message);
        }
    }

}
