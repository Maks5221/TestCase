package com.example.testcase.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Goods {

    private Long id;

    @NotBlank
    @Length(max = 255)
    private String name;

    @Length(max = 4096)
    private String description;

    @Min(0)
    private double price;

    private String availability = "Not available";
}
