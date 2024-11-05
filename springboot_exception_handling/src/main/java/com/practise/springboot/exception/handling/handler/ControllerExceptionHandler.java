package com.practise.springboot.exception.handling.handler;

import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.practise.springboot.exception.handling.controller.HttpStatusResponse;
import com.practise.springboot.exception.handling.exception.SampleException;
import com.practise.springboot.exception.handling.exception.SampleExcetionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpStatusResponse> handleNoHandlerFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new HttpStatusResponse(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpStatusResponse> handleMethodNotAllowedException() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new HttpStatusResponse(HttpStatus.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(SampleException.class)
    @ResponseBody
    public ResponseEntity<SampleExcetionResponse> handleSampleException(SampleException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                new SampleExcetionResponse(
                    e.getErrors()
                        .stream()
                        .map(
                            error -> error.withMessage(
                                messageSource.getMessage(
                                    error.getCode(),
                                    error.getArgs().toArray(ArrayUtils.EMPTY_OBJECT_ARRAY),
                                    error.getCode(),
                                    Locale.getDefault())))
                        .toList()));
    }
}
