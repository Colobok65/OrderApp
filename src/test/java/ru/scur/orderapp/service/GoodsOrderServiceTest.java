package ru.scur.orderapp.service;

import org.junit.jupiter.api.Test;
import ru.scur.orderapp.converter.GoodsOrderConverter;
import ru.scur.orderapp.dao.GoodsOrderDAO;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.util.GoodsOrderDTOUtil;
import ru.scur.orderapp.util.GoodsOrderUtil;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoodsOrderServiceTest {

    private final GoodsOrderDAO goodsOrderDAOMock = mock(GoodsOrderDAO.class);

    private final GoodsOrderConverter goodsOrderConverter = new GoodsOrderConverter();

    private final GoodsOrderService goodsOrderService =
            new GoodsOrderService(goodsOrderDAOMock, goodsOrderConverter);

    @Test
    void shouldCreateOrder() {
        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
        GoodsOrder sss = new GoodsOrder(null, goodsOrder.getClient(), goodsOrder.getDate(), goodsOrder.getAddress(), null);
        when(goodsOrderDAOMock.save(any())).thenReturn(goodsOrder);
        GoodsOrderDTO actual = goodsOrderService.createGoodsOrder(expected);

        assertEquals(expected, actual);

        verify(goodsOrderDAOMock).save(sss);
    }

    @Test
    void shouldGetOrder() {
        Long id = 10L;
        GoodsOrder expected = GoodsOrderUtil.getGoodsOrder();
        when(goodsOrderDAOMock.findById(any())).thenReturn(Optional.of(expected));
        GoodsOrder actual = goodsOrderService.getGoodsOrder(id);

        assertEquals(expected, actual);

        verify(goodsOrderDAOMock).findById(id);
    }

    @Test
    void shouldGetOrderById() {
        Long id = 10L;
        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
        when(goodsOrderDAOMock.findById(any())).thenReturn(Optional.of(goodsOrder));

        GoodsOrderDTO actual = goodsOrderService.getGoodsOrderById(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldEditOrder() {
        Long id = 10L;
        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
        when(goodsOrderDAOMock.findById(any())).thenReturn(Optional.of(goodsOrder));
        when(goodsOrderDAOMock.save(any())).thenReturn(goodsOrder);

        GoodsOrderDTO actual = goodsOrderService.editGoodsOrder(id, expected);

        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteOrderById() {
        Long id = 10L;

        goodsOrderService.deleteGoodsOrderById(id);

        verify(goodsOrderDAOMock).deleteById(id);
    }

    @Test
    void shouldGetAllOrders() {
        List<GoodsOrderDTO> expected = new ArrayList<>();
        when(goodsOrderDAOMock.findAll()).thenReturn(new ArrayList<>());

        List<GoodsOrderDTO> actual = goodsOrderService.getAllGoodsOrders();

        assertEquals(expected, actual);
        verify(goodsOrderDAOMock).findAll();
    }
}