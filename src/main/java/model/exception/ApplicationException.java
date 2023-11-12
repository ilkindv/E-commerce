package model.exception;

import lombok.Getter;
import model.enums.ExceptionEnum;

import java.time.LocalDateTime;
@Getter
public class ApplicationException extends RuntimeException {
    private final LocalDateTime exceptionTime;
    public ApplicationException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.exceptionTime = LocalDateTime.now();
    }
}
