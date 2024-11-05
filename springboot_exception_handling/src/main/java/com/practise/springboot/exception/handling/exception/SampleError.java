package com.practise.springboot.exception.handling.exception;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.With;

@Getter
@Builder
public class SampleError {
    private String code;

    @Singular
    private List<Object> args;

    @With
    private String message;

    public static SampleError of(@NonNull final String code) {
        return SampleError.builder().code(code).build();
    }

    public static SampleError of(@NonNull final String code, final Object... args) {
        if (isEmpty(args)) {
            return SampleError.builder().code(code).build();
        }
        return SampleError.builder().code(code).args(Arrays.asList(args)).build();
    }

    @Override
    public String toString() {
        return code + Optional.ofNullable(StringUtils.join(args)).map(it -> ": [" + it + "]").orElse(StringUtils.EMPTY);
    }
}
