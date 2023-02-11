package com.drobov.project.project_calendar.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;



@Target({ ANNOTATION_TYPE, TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {CheckTimeWorkValidator.class, CheckTimeDateValidator.class})
@Documented
public @interface CheckTime {

    String message() default "End time can\''t be earlier then start time";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
