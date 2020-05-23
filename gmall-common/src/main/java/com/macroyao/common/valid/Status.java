package com.macroyao.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {StatusConstraintValidator.class})  //绑定注解所依赖的解析器，可以绑定多个类型解析器
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface Status {
    /*下面三个不可缺少*/
    String message() default "{com.macroyao.common.valid.Status.message}";  //注解的相对路径.message,也可以自定义ValidationMessages.properties加入

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int [] values() default {};
}
