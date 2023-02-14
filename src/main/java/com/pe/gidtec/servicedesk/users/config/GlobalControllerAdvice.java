package com.pe.gidtec.servicedesk.users.config;

import com.pe.gidtec.servicedesk.users.common.model.api.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.info("handleMethodArgumentNotValidException");
        ResultResponse<Void>  response = ResultResponse.<Void>builder()
                .responseCode("10")
                .build();

        StringBuilder message = new StringBuilder();
        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()) {
            message.append(e.getDefaultMessage());
        }
        response.setResponseMessage(message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ResultResponse<Void>> handleException(WebExchangeBindException e) {
        log.info("WebExchangeBindException : {}", e);
        ResultResponse<Void>  response = ResultResponse.<Void>builder()
                .responseCode("10")
                .build();

        StringBuilder message = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()){
            message.append(fieldError.getDefaultMessage());
            message.append("\n");
        }
        response.setResponseMessage(message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
