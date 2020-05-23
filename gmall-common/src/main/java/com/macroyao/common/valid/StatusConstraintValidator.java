package com.macroyao.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 第一个泛型为要自定义的校检注解，第二个泛型为注解要校检的参数类型
 */
public class StatusConstraintValidator implements ConstraintValidator<Status, Integer> {

    Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(Status constraintAnnotation) {
        int[] values = constraintAnnotation.values(); //拿到values标识的值
        if(values!=null&&values.length!=0){
            for (int value : values) {
                set.add(value);
            }
        }
    }

    /**
     *
     * @param value 当前用户传入的值
     * @param context  注解环境
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value)?true:false;
    }
}
