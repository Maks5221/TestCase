package com.example.testcase.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationExceptionErrorResponse {

    private final List<Violation> violations;
}
