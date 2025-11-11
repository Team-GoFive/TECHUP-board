package com.kt.board.exception.handler;

import com.kt.board.common.api.ApiErrorResponse;
import com.kt.board.constants.message.ErrorCode;
import com.kt.board.exception.BaseException;
import com.kt.board.exception.CustomException;
import com.kt.board.exception.handler.base.BaseExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class SpringExceptionHandler extends BaseExceptionHandler{

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiErrorResponse> customException(BaseException ex) {
        return toResponse(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        String details = Arrays.toString(e.getDetailMessageArguments());
        String message = details.split(",", 2)[1].replace("]", "").trim();
        ErrorCode error = ErrorCode.BODY_FIELD_ERROR;
        String resultMessage = error.format(message);
        return ApiErrorResponse.error(error.getStatus(), resultMessage);
    }


}
