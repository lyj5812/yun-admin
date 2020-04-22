package com.lyj.common.error;
import com.lyj.pojo.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ExceptionAdviceHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R unKnowExceptionHandler(Exception e) {
        e.printStackTrace();
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}

