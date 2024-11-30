package com.example.testcase.mapper;

import com.example.testcase.dto.GoodsDto;
import com.example.testcase.entity.Goods;

public class GoodsMapper {

    public static Goods mapToGoods(GoodsDto goodsDto) {
        return new Goods(
                goodsDto.id(),
                goodsDto.name(),
                goodsDto.description(),
                goodsDto.price(),
                goodsDto.availability()
        );
    }

    public static GoodsDto mapToGoodsDto(Goods goods) {
        return new GoodsDto(
                goods.getId(),
                goods.getName(),
                goods.getDescription(),
                goods.getPrice(),
                goods.getAvailability()
        );
    }
}
