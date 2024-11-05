package com.practise.springboot.exception.handling.exception;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public record SampleExcetionResponse(@Singular List<SampleError> errors) {
    public SampleExcetionResponse(@NonNull final SampleException e) {
        this(e.getErrors());
    }
}
