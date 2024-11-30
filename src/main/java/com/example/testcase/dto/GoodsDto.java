package com.example.testcase.dto;

import com.example.testcase.entity.Availability;

public record GoodsDto(Long id, String name, String description, double price, Availability availability) {
}
