package com.example.testcase.controller;

import com.example.testcase.entity.Goods;
import com.example.testcase.service.GoodsService;
import com.example.testcase.validation.ValidationException;
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
    public ResponseEntity<?> create(@RequestBody Goods goods) {
        try {
            goodsService.create(goods);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
        return ResponseEntity.ok("Goods created");
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Goods>> read() {
        List<Goods> goods = goodsService.getAll();

        return goods != null &&  !goods.isEmpty()
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Goods> getGoodsById(@PathVariable(name = "id") Long id) {
        Goods goods = goodsService.getGoodsById(id);

        return goods != null
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Goods goods) {
        try {
             goodsService.update(id, goods);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        boolean deleted = goodsService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
