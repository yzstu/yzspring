package yzspring.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@YzComponent
@Documented
public @interface YzRequestParam {
    String value() default "";
}
