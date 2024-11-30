package com.example.testcase.service;

import com.example.testcase.dto.GoodsDto;

import java.util.List;

public interface GoodsService {

    GoodsDto createGoods(GoodsDto goodsDto);

    List<GoodsDto> getAll();

    GoodsDto getGoodsById(Long id);

    GoodsDto updateGoods(Long id, GoodsDto goodsDto);

    void deleteGoods(Long id);
}
