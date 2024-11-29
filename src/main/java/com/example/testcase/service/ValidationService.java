package com.example.testcase.service;

import com.example.testcase.entity.Goods;
import com.example.testcase.validation.ValidationException;
import com.example.testcase.validation.Violation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final Validator validator;

    public boolean isValidGoods(Goods goods) throws ValidationException {
        Set<ConstraintViolation<Goods>> constraintViolations = validator.validate(goods);

        if (!constraintViolations.isEmpty()) {
            throw new ValidationException(buildViolationsList(constraintViolations));
        }
        return true;
    }

    private <T> List<Violation> buildViolationsList(Set<ConstraintViolation<T>> constraintViolations) {
        return constraintViolations.stream()
                                    .map(violation -> new Violation(
                                            violation.getPropertyPath().toString(),
                                            violation.getMessage()
                                    )).toList();
    }
}
