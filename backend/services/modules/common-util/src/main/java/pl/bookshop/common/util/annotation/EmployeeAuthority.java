package pl.bookshop.common.util.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasAnyAuthority(T(pl.bookshop.common.util.entity.RolesEnum).ROLE_EMPLOYEE.name())")
public @interface EmployeeAuthority {
    @AliasFor(annotation = PreAuthorize.class)
    String value() default "hasAnyAuthority(T(pl.bookshop.common.util.entity.RolesEnum).ROLE_EMPLOYEE.name())";
}