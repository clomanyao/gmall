package com.macroyao.gmall.product.exception;

import com.macroyao.common.exception.BizCodeEnum;
import com.macroyao.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = {"com.macroyao.gmall.product.controller"})
public class GmallExceptionControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)  //精确优先，找不到后再去Throwable
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("handle valid exception:{},msg:{}",e,e.getMessage());
        Map<String, Object> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        result.getFieldErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            String field = error.getField();
            map.put(field, message);
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION).put("data",map);
    }


    @ExceptionHandler(Throwable.class)
    public R handleException(Throwable e) {
        log.error("handle exception:{},msg:{}",e,e.getMessage());
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION);
    }

}
