package ru.scur.orderapp.service;

import org.junit.jupiter.api.Test;
import ru.scur.orderapp.converter.GoodsOrderConverter;
import ru.scur.orderapp.dao.GoodsOrderDAO;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.util.GoodsOrderDTOUtil;
import ru.scur.orderapp.util.GoodsOrderUtil;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoodsGoodsOrderServiceTest {

    private final GoodsOrderDAO goodsOrderDAOMock = mock(GoodsOrderDAO.class);
    private  final GoodsOrderConverter goodsOrderConverterMock = mock(GoodsOrderConverter.class);
    private  final GoodsOrderService goodsOrderService =
            new GoodsOrderService(goodsOrderDAOMock, goodsOrderConverterMock);

    @Test
    void shouldCreateOrder() {

        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
//        GoodsOrder sss = new GoodsOrder(null, goodsOrder.getClient(), goodsOrder.getDate(), goodsOrder.getAddress(), null);

        when(goodsOrderConverterMock.toOrderDTO(any())).thenReturn(expected);
        when(goodsOrderDAOMock.save(any())).thenReturn(goodsOrder);

        GoodsOrderDTO actual = goodsOrderService.createOrder(expected);

        assertEquals(expected, actual);

//        verify(goodsOrderDAOMock).save(sss);
        verify(goodsOrderConverterMock).toOrderDTO(goodsOrder);
    }

    @Test
    void shouldGetOrder() {
        Long id = 10L;
        GoodsOrder expected = GoodsOrderUtil.getGoodsOrder();

        when(goodsOrderDAOMock.findById(any())).thenReturn(Optional.of(GoodsOrderUtil.getGoodsOrder()));

        GoodsOrder actual = goodsOrderService.getOrder(id);

        assertEquals(expected, actual);

        verify(goodsOrderDAOMock).findById(id);
    }

    @Test
    void shouldGetOrderById() {
        Long id = 10L;

        GoodsOrderDTO excepted = GoodsOrderDTOUtil.getGoodsOrderDTO();

        when(goodsOrderConverterMock.toOrderDTO(any())).thenReturn(excepted);

        GoodsOrderDTO actual = goodsOrderService.getOrderById(id);

        assertEquals(excepted, actual);

        verify(goodsOrderConverterMock).toOrderDTO(GoodsOrderUtil.getGoodsOrder());
    }

    @Test
    void shouldEditOrder() {
    }

    @Test
    void shouldDeleteOrderById() {
    }

    @Test
    void shouldGetAllOrders() {
    }
}