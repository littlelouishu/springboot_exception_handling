package com.practise.springboot.exception.handling.exception;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Getter
@NoArgsConstructor
public class SampleException extends Exception {
    private static final long serialVersionUID = 1L;

    private final List<SampleError> errors = new ArrayList<>();

    public static SampleException of(@NonNull final String code) {
        return new SampleException(SampleError.of(code));
    }

    public static SampleException of(@NonNull final String code, final Object... args) {
        if (isEmpty(args)) {
            return new SampleException(SampleError.of(code));
        }
        return new SampleException(SampleError.of(code, args));
    }

    public SampleException(@NonNull final SampleError error) {
        this.errors.add(error);
    }

    public void add(@NonNull final String code, final Object... args) {
        if (isEmpty(args)) {
            add(SampleError.of(code));
        } else {
            add(SampleError.of(code, args));
        }
    }

    public void add(@NonNull final SampleError error) {
        this.errors.add(error);
    }

    public void addAll(@NonNull final Collection<SampleError> errors) {
        this.errors.addAll(errors);
    }

    public void merge(@NonNull final SampleException e) {
        addAll(e.getErrors());
    }

    public void throwIfNecessary() throws SampleException {
        if (!getErrors().isEmpty()) {
            throw this;
        }
    }

    @Override
    public String getMessage() {
        return toString();
    }
}
