package donggrami.earth1round.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static donggrami.earth1round.config.BaseResponseStatus.VALIDATION_ERROR;
import static donggrami.earth1round.config.BaseResponseStatus.VALIDATION_TYPE_ERROR;

@RestController
@ControllerAdvice
//extends ResponseEntityExceptionHandler
public class BaseExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<Object> handleBaseExceptions(BaseException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .isSuccess(exception.getStatus().isSuccess())
                .code(exception.getStatus().getCode())
                .message(exception.getStatus().getMessage())
                .build();
        return new ResponseEntity(exceptionResponse, exception.getHttpStatus());
    }

    // 유효한 값이 아닐 때 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .isSuccess(false)
                .code(VALIDATION_ERROR.getCode())
                .message(VALIDATION_ERROR.getMessage())
                .build();
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // 값의 타입이 올바르지 않을 때 예외 처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Map<String, String>> handleValidationExceptions(HttpMessageNotReadableException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .isSuccess(false)
                .code(VALIDATION_TYPE_ERROR.getCode())
                .message(VALIDATION_TYPE_ERROR.getMessage())
                .build();
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
