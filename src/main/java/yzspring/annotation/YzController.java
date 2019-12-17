package yzspring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@YzComponent
@Documented
public @interface YzController {
    String value() default "";
}
