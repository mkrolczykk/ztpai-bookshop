package pl.bookshop.auth.util.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasAnyAuthority(T(pl.bookshop.auth.util.entity.RolesEnum).ROLE_ADMIN.name())")
public @interface AdminAuthority {
    @AliasFor(annotation = PreAuthorize.class)
    String value() default "hasAnyAuthority(T(pl.bookshop.auth.util.entity.RolesEnum).ROLE_ADMIN.name())";
}
