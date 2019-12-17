package yzspring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@YzComponent
@Documented
public @interface YzRequestMapping {
    String value() default "";
}
