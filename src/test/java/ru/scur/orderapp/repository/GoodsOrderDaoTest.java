package ru.scur.orderapp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class GoodsOrderDaoTest {
    @Autowired
    private GoodsOrderRepository goodsOrderRepository;

    @BeforeEach
    public void setUp() {
        goodsOrderRepository.save(new GoodsOrder(null, "client", new Date(), "address", null));
    }

    @AfterEach
    public void tearDown() {
        goodsOrderRepository.deleteAll();
    }

    @Test
    public void shouldFindGoodsOrderById() {
        GoodsOrder expected = goodsOrderRepository.save(
                new GoodsOrder(null, "client 1", new Date(), "address 1", null)
        );

        GoodsOrder actual = goodsOrderRepository.findById(expected.getId()).get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getClient(), actual.getClient());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getAddress(), actual.getAddress());
    }

    @Test
    public void shouldDeleteGoodsOrderById(){
        GoodsOrder goodsOrder = goodsOrderRepository.save(
                new GoodsOrder(null, "client 1", new Date(), "address 1", null)
        );

        goodsOrderRepository.deleteById(goodsOrder.getId());

        assertEquals(1, goodsOrderRepository.findAll().size());
    }

    @Test
    public void shouldFindAllGoodsOrder(){
        goodsOrderRepository.save(
                new GoodsOrder(null, "client 1", new Date(), "address 1", null)
        );

        assertEquals(2, goodsOrderRepository.findAll().size());
    }

    @Test
    public void shouldSaveGoodsOrder(){
        assertEquals(1, goodsOrderRepository.findAll().size());
    }

    @Test
    public void shouldEditGoodsOrder(){
        String client = "client 1";
        GoodsOrder goodsOrder = goodsOrderRepository.findAll().get(0);
        goodsOrder.setClient(client);
        goodsOrderRepository.save(goodsOrder);

        GoodsOrder edited = goodsOrderRepository.findById(goodsOrder.getId()).get();

        assertEquals(client, edited.getClient());
    }

}