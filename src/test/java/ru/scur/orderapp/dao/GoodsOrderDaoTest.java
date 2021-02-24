package ru.scur.orderapp.dao;

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
    private GoodsOrderDAO goodsOrderDAO;

    @BeforeEach
    public void setUp() {
        goodsOrderDAO.save(new GoodsOrder(null, "client", new Date(), "address", null));
    }

    @AfterEach
    public void tearDown() {
        goodsOrderDAO.deleteAll();
    }

    @Test
    public void shouldFindGoodsOrderById() {
        GoodsOrder expected = goodsOrderDAO.save(
                new GoodsOrder(null, "client 1", new Date(), "address 1", null)
        );

        GoodsOrder actual = goodsOrderDAO.findById(expected.getId()).get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getClientName(), actual.getClientName());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getAddress(), actual.getAddress());
    }

    @Test
    public void shouldDeleteGoodsOrderById(){
        GoodsOrder goodsOrder = goodsOrderDAO.save(
                new GoodsOrder(null, "client 1", new Date(), "address 1", null)
        );

        goodsOrderDAO.deleteById(goodsOrder.getId());

        assertEquals(1, goodsOrderDAO.findAll().size());
    }

    @Test
    public void shouldFindAllGoodsOrder(){
        goodsOrderDAO.save(
                new GoodsOrder(null, "client 1", new Date(), "address 1", null)
        );

        assertEquals(2, goodsOrderDAO.findAll().size());
    }

    @Test
    public void shouldSaveGoodsOrder(){
        assertEquals(1, goodsOrderDAO.findAll().size());
    }

    @Test
    public void shouldEditGoodsOrder(){
        String client = "client 1";
        GoodsOrder goodsOrder = goodsOrderDAO.findAll().get(0);
        goodsOrder.setClientName(client);
        goodsOrderDAO.save(goodsOrder);

        GoodsOrder edited = goodsOrderDAO.findById(goodsOrder.getId()).get();

        assertEquals(client, edited.getClientName());
    }

}
