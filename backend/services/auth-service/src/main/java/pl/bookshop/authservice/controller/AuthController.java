package pl.bookshop.authservice.controller;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bookshop.auth.util.annotation.AdminAuthority;
import pl.bookshop.auth.util.dto.EmployeesListDto;
import pl.bookshop.authservice.dto.request.AuthRequest;
import pl.bookshop.authservice.dto.request.EmailValidationRequest;
import pl.bookshop.authservice.dto.request.RegisterRequest;
import pl.bookshop.authservice.dto.request.UserNameValidationRequest;
import pl.bookshop.authservice.dto.response.AuthResponse;
import pl.bookshop.authservice.service.AuthService;
import pl.bookshop.auth.util.config.UserInfoUserDetails;
import pl.bookshop.auth.util.entity.UserInfo;
import pl.bookshop.auth.util.exception.dto.ValidationErrorList;
import pl.bookshop.auth.util.mapper.DTO;
import pl.bookshop.auth.util.messages.MessagesEnum;
import pl.bookshop.auth.util.messages.Translator;
import pl.bookshop.auth.util.service.JwtService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@DTO(RegisterRequest.class) UserInfo userInfo) {
        authService.registerUser(userInfo);
        return ResponseEntity.ok().build();
    }

    @AdminAuthority
    @PostMapping("/register/employee")
    public ResponseEntity<?> registerEmployee(@DTO(RegisterRequest.class) UserInfo userInfo) {
        authService.registerEmployee(userInfo);
        return ResponseEntity.ok().build();
    }

    @ApiResponse(headers = @Header(name = HttpHeaders.AUTHORIZATION))
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            UserInfoUserDetails details = (UserInfoUserDetails) authentication.getPrincipal();
            AuthResponse response = AuthResponse.builder()
                    .name(details.getName())
                    .surname(details.getSurname())
                    .username(details.getUserName())
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtService.generateToken(authRequest.getEmail()))
                    .header("user-role", details.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "content-type, cache-control, authorization, user-role, expires, pragma")
                    .body(response);
        } catch (AuthenticationException e) {
            ValidationErrorList body =
                    ValidationErrorList.of(AuthRequest.Fields.password, Translator.translate(MessagesEnum.INVALID_EMAIL_OR_PASSWORD));
            return body.createResponseEntity();
        }
    }

    @PostMapping("/register/email/validate")
    public ResponseEntity<?> validateEmail(@Valid @RequestBody EmailValidationRequest dto) {
        if (authService.existsByEmail(dto.getEmail())) {
            String fieldName = EmailValidationRequest.Fields.email;
            ValidationErrorList body = ValidationErrorList.of(fieldName, Translator.translate(MessagesEnum.EXISTS_EMAIL));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/username/validate")
    public ResponseEntity<?> validateEmail(@RequestBody UserNameValidationRequest dto) {
        if (authService.existsByUsername(dto.getUsername())) {
            String fieldName = UserNameValidationRequest.Fields.username;
            ValidationErrorList body = ValidationErrorList.of(fieldName, Translator.translate(MessagesEnum.EXISTS_USER_NAME));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
        return ResponseEntity.ok().build();
    }

    @AdminAuthority
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeesListDto>> getEmployees() {
        return ResponseEntity.ok(authService.getEmployees());
    }

}
