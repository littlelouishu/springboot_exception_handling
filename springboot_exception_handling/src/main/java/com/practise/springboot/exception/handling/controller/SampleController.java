package com.practise.springboot.exception.handling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.springboot.exception.handling.exception.SampleCheckedException;
import com.practise.springboot.exception.handling.exception.SampleException;
import com.practise.springboot.exception.handling.service.SampleService;

@RestController
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @GetMapping("/demo1")
    public ResponseEntity<String> demo1() throws SampleException {
        return ResponseEntity.ok("{\"result\":\"success\"}");
    }

    @GetMapping("/demo2")
    public ResponseEntity<String> demo2() throws SampleException {
        SampleException exception = SampleException.of("error1");
        exception.add("error2");
        throw exception;
    }

    @GetMapping("/demo3")
    public ResponseEntity<String> demo3() throws SampleCheckedException {
        return ResponseEntity.ok(sampleService.sampleMethodOfJavaStream());
    }

    @GetMapping("/demo4")
    public ResponseEntity<String> demo4() throws SampleCheckedException {
        return ResponseEntity.ok(sampleService.sampleMethodOfEnhancedLoop());
    }
}
