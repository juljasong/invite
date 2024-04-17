package test.invite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import test.invite.exception.CommonException;
import test.invite.response.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidExceptionHandler(MethodArgumentNotValidException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            errorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return errorResponse;
    }

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> commonException(CommonException e) {

        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(body);
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseEntity<ErrorResponse> handleException(Exception e) {
//
//        log.error(e.getMessage());
//
//        ErrorResponse body = ErrorResponse.builder()
//                .code(String.valueOf(500))
//                .message("INTERNAL_SERVER_ERROR")
//                .build();
//
//        return ResponseEntity.status(500).body(body);
//    }

}
