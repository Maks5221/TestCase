package com.example.testcase.service;

import com.example.testcase.entity.Goods;
import com.example.testcase.validation.ValidationException;

import java.util.List;

public interface GoodsService {

    void create(Goods goods) throws ValidationException;

    List<Goods> getAll();

    Goods getGoodsById(Long id);

    boolean update(Long id, Goods goods) throws ValidationException;

    boolean delete(Long id);
}
