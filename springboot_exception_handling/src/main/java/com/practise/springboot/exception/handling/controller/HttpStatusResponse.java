package com.practise.springboot.exception.handling.controller;

import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record HttpStatusResponse(int code, String value, String reasonPhrase, String cause) {
    public HttpStatusResponse {
        Objects.requireNonNull(value);
        Objects.requireNonNull(reasonPhrase);
    }

    public HttpStatusResponse(@NonNull final HttpStatus httpStatus) {
        this(httpStatus.value(), httpStatus.toString(), httpStatus.getReasonPhrase(), null);
    }

    public HttpStatusResponse(@NonNull final HttpStatus httpStatus, @NonNull final Exception e) {
        this(httpStatus.value(), httpStatus.toString(), httpStatus.getReasonPhrase(), ExceptionUtils.getStackTrace(e));
    }
}
