package com.example.mission3_basic.Area;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {
    String message() default "주소를 다시 입력해주세요";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
