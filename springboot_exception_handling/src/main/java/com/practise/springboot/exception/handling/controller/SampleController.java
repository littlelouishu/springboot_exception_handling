package com.practise.springboot.exception.handling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.springboot.exception.handling.exception.SampleException;

@RestController
public class SampleController {
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
}
