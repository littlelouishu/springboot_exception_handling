package com.practise.springboot.exception.handling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practise.springboot.exception.handling.exception.SampleCheckedException;
import com.practise.springboot.exception.handling.utils.LambdaExceptionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleService {
    public String sampleMethodOfJavaStream() throws SampleCheckedException {
        return List.of(1, 2, 3)
            .stream()
            .map(LambdaExceptionUtil.rethrowFunction(this::sampleMethodWithCheckedException))
            .toList()
            .toString();
    }

    public String sampleMethodOfEnhancedLoop() throws SampleCheckedException {
        List.of(1, 2, 3).forEach(LambdaExceptionUtil.rethrowConsumer(this::sampleMethodWithCheckedException));

        return "success";
    }

    private String sampleMethodWithCheckedException(int input) throws SampleCheckedException {
        log.info("execute sampleMethodWithCheckedException");
        throw new SampleCheckedException("Checked Exception occurred");
    }
}
