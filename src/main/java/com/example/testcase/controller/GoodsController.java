package com.example.testcase.controller;

import com.example.testcase.dto.GoodsDto;
import com.example.testcase.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping("/create")
    public ResponseEntity<GoodsDto> createGoods (@RequestBody GoodsDto goodsDto) {
        var goods = goodsService.createGoods(goodsDto);
        return new ResponseEntity<>(goods, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<GoodsDto>> getAll() {
        var all = goodsService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GoodsDto> getGoodsById(@PathVariable(name = "id") Long id) {
        var goodsById = goodsService.getGoodsById(id);
        return ResponseEntity.ok(goodsById);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GoodsDto> updateGoods(@PathVariable(name = "id") Long id, @RequestBody GoodsDto goodsDto) {
        var updateGoods = goodsService.updateGoods(id, goodsDto);
        return ResponseEntity.ok(updateGoods);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteGoods(@PathVariable(name = "id") Long id) {
        goodsService.deleteGoods(id);
        return ResponseEntity.ok("Goods deleted successfully");
    }
}
