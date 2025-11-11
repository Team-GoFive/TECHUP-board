package com.kt.board.exception;

import com.kt.board.constants.message.ErrorCode;
import lombok.Getter;
import org.springframework.core.NestedRuntimeException;

@Getter
public class BaseException extends NestedRuntimeException {

    private ErrorCode error;

    public BaseException(ErrorCode error) {
        super(error.name());
        this.error = error;
    }


}
