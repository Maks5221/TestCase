package com.example.testcase.service;

import com.example.testcase.dto.GoodsDto;
import com.example.testcase.exception.ResourceNotFoundException;
import com.example.testcase.mapper.GoodsMapper;
import com.example.testcase.repository.GoodsRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Override
    @Transactional
    public GoodsDto createGoods(@Valid GoodsDto goodsDto) {
        var goods = GoodsMapper.mapToGoods(goodsDto);
        var savedGoods = goodsRepository.save(goods);
        return GoodsMapper.mapToGoodsDto(savedGoods);
    }

    @Override
    @Transactional
    public List<GoodsDto> getAll() {
        return goodsRepository.findAll()
                .stream().map(GoodsMapper::mapToGoodsDto)
                .toList();
    }

    @Override
    @Transactional
    public GoodsDto getGoodsById(Long id) {
        var goods = goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods is not exists with given ID: " + id));
        return GoodsMapper.mapToGoodsDto(goods);
    }

    @Override
    @Transactional
    public GoodsDto updateGoods(Long id, @Valid GoodsDto goodsDto) {
        var goods = goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods is not exists with given ID: " + id));

        goods.setName(goodsDto.name());
        goods.setDescription(goodsDto.description());
        goods.setPrice(goodsDto.price());
        goods.setAvailability(goodsDto.availability());

        goodsRepository.save(goods);

        return GoodsMapper.mapToGoodsDto(goods);
    }

    @Override
    @Transactional
    public void deleteGoods(Long id) {
        var goods = goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods is not exists with given ID: " + id));
        goodsRepository.delete(goods);
    }
}
