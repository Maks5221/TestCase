package com.example.testcase.service;

import com.example.testcase.entity.Goods;
import com.example.testcase.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private static final Map<Long, Goods> GOODS_MAP = new HashMap<>();

    private static final AtomicLong GOODS_ID = new AtomicLong();

    private final Validator validator;

    private final ValidationService validationService;

    @Override
    public void create(Goods goods) throws ValidationException {
        if (validationService.isValidGoods(goods)) {
            final Long goodId = GOODS_ID.incrementAndGet();
            goods.setId(goodId);
            GOODS_MAP.put(goodId, goods);
        }
    }

    @Override
    public List<Goods> getAll() {
        return new ArrayList<>(GOODS_MAP.values());
    }

    @Override
    public Goods getGoodsById(Long id) {
        return GOODS_MAP.get(id);
    }

    @Override
    public boolean update(Long id, Goods goods) throws ValidationException {
        if (validationService.isValidGoods(goods) && GOODS_MAP.containsKey(id)) {
            goods.setId(id);
            GOODS_MAP.put(id, goods);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return GOODS_MAP.remove(id) != null;
    }
}
