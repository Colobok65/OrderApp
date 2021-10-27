package ru.scur.orderapp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.scur.orderapp.model.GoodsOrder;
import ru.scur.orderapp.util.GoodsOrderUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class GoodsOrderDaoTest {
    @Autowired
    private GoodsOrderRepository goodsOrderRepository;

    @BeforeEach
    public void setUp() {
        goodsOrderRepository.save(GoodsOrderUtil.getGoodsOrder());
    }

    @AfterEach
    public void tearDown() {
        goodsOrderRepository.deleteAll();
    }

    @Test
    public void shouldFindGoodsOrderById() {
        GoodsOrder expected = goodsOrderRepository.findAll().get(0);

        GoodsOrder actual = goodsOrderRepository.findById(expected.getId()).get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getUser(), actual.getUser());
    }

    @Test
    public void shouldDeleteGoodsOrderById(){
        GoodsOrder goodsOrder = goodsOrderRepository.findAll().get(0);

        goodsOrderRepository.deleteById(goodsOrder.getId());

        assertEquals(0, goodsOrderRepository.findAll().size());
    }

    @Test
    public void shouldFindAllGoodsOrder(){
        assertEquals(1, goodsOrderRepository.findAll().size());
    }

    @Test
    public void shouldSaveGoodsOrder(){
        assertEquals(1, goodsOrderRepository.findAll().size());
    }

    @Test
    public void shouldEditGoodsOrder(){
        GoodsOrder goodsOrder = goodsOrderRepository.findAll().get(0);
        LocalDateTime time = LocalDateTime.now();
        goodsOrder.setDate(time);
        GoodsOrder changed = goodsOrderRepository.save(goodsOrder);

        assertEquals(time, changed.getDate());
    }
}