package ru.scur.orderapp.service;

import org.junit.jupiter.api.Test;
import ru.scur.orderapp.converter.GoodsOrderConverter;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.model.GoodsOrder;
import ru.scur.orderapp.repository.GoodsOrderRepository;
import ru.scur.orderapp.repository.UserRepository;
import ru.scur.orderapp.util.GoodsOrderDTOUtil;
import ru.scur.orderapp.util.GoodsOrderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoodsOrderServiceTest {

    private final GoodsOrderRepository goodsOrderRepositoryMock = mock(GoodsOrderRepository.class);
    private final UserRepository userRepositoryMock = mock(UserRepository.class);
    private final GoodsOrderConverter goodsOrderConverterMock = mock(GoodsOrderConverter.class);

    private final GoodsOrderService goodsOrderService =
            new GoodsOrderService(goodsOrderRepositoryMock, goodsOrderConverterMock, userRepositoryMock);

    @Test
    void shouldCreateGoodsOrder() {
        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
        when(userRepositoryMock.getOne(any())).thenReturn(goodsOrder.getUser());
        when(goodsOrderConverterMock.toOrderDTO(any())).thenReturn(expected);
        GoodsOrderDTO actual = goodsOrderService.createGoodsOrder(expected);

        assertEquals(expected, actual);

        verify(userRepositoryMock).getOne(any());
        verify(goodsOrderConverterMock).toOrderDTO(any());
    }

    @Test
    void shouldGetGoodsOrder() {
        Long id = 10L;
        GoodsOrder expected = GoodsOrderUtil.getGoodsOrder();
        when(goodsOrderRepositoryMock.findById(any())).thenReturn(Optional.of(expected));
        GoodsOrder actual = goodsOrderService.getGoodsOrder(id);

        assertEquals(expected, actual);

        verify(goodsOrderRepositoryMock).findById(id);
    }

    @Test
    void shouldGetGoodsOrderById() {
        Long id = 10L;
        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
        when(goodsOrderRepositoryMock.findById(any())).thenReturn(Optional.of(goodsOrder));
        when(goodsOrderConverterMock.toOrderDTO(any())).thenReturn(expected);
        GoodsOrderDTO actual = goodsOrderService.getGoodsOrderById(id);

        assertEquals(expected, actual);

        verify(goodsOrderRepositoryMock).findById(id);
        verify(goodsOrderConverterMock).toOrderDTO(any());
    }

    @Test
    void shouldDeleteOrderById() {
        Long id = 10L;
        goodsOrderService.deleteGoodsOrderById(id);

        verify(goodsOrderRepositoryMock).deleteById(id);
    }

    @Test
    void shouldGetAllOrders() {
        List<GoodsOrderDTO> expected = new ArrayList<>();
        when(goodsOrderRepositoryMock.findAll()).thenReturn(new ArrayList<>());

        List<GoodsOrderDTO> actual = goodsOrderService.getAllGoodsOrders();

        assertEquals(expected, actual);

        verify(goodsOrderRepositoryMock).findAll();
    }

    @Test
    void shouldGetAllGoodsOrdersByUserId() {
        Long userId = 10L;
        when(goodsOrderRepositoryMock.findGoodsOrdersByUserId(any())).thenReturn(new ArrayList<>());
    }
}
