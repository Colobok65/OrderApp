package ru.scur.orderapp.service;

class GoodsOrderRepositoryTest {
//
//    private final GoodsOrderRepository goodsOrderRepositoryMock = mock(GoodsOrderRepository.class);
//    private final OrderLineConverter orderLineConverterMock = mock(OrderLineConverter.class);
//
//    private final GoodsOrderConverter goodsOrderConverter = new GoodsOrderConverter(orderLineConverterMock, userConverter);
//
//    private final ru.scur.orderapp.service.GoodsOrderService goodsOrderService =
//            new ru.scur.orderapp.service.GoodsOrderService(goodsOrderRepositoryMock, goodsOrderConverter, userRepository);
//
//    @Test
//    void shouldCreateOrder() {
//        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
//        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
//        GoodsOrder sss = new GoodsOrder(null, goodsOrder.getClient(), goodsOrder.getDate(), goodsOrder.getAddress(), null);
//        when(goodsOrderRepositoryMock.save(any())).thenReturn(goodsOrder);
//        GoodsOrderDTO actual = goodsOrderService.createGoodsOrder(expected);
//
//        assertEquals(expected, actual);
//
//        verify(goodsOrderRepositoryMock).save(sss);
//    }
//
//    @Test
//    void shouldGetOrder() {
//        Long id = 10L;
//        GoodsOrder expected = GoodsOrderUtil.getGoodsOrder();
//        when(goodsOrderRepositoryMock.findById(any())).thenReturn(Optional.of(expected));
//        GoodsOrder actual = goodsOrderService.getGoodsOrder(id);
//
//        assertEquals(expected, actual);
//
//        verify(goodsOrderRepositoryMock).findById(id);
//    }
//
//    @Test
//    void shouldGetOrderById() {
//        Long id = 10L;
//        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
//        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
//        when(goodsOrderRepositoryMock.findById(any())).thenReturn(Optional.of(goodsOrder));
//
//        GoodsOrderDTO actual = goodsOrderService.getGoodsOrderById(id);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldEditOrder() {
//        Long id = 10L;
//        GoodsOrder goodsOrder = GoodsOrderUtil.getGoodsOrder();
//        GoodsOrderDTO expected = GoodsOrderDTOUtil.getGoodsOrderDTO();
//        when(goodsOrderRepositoryMock.findById(any())).thenReturn(Optional.of(goodsOrder));
//        when(goodsOrderRepositoryMock.save(any())).thenReturn(goodsOrder);
//
//        GoodsOrderDTO actual = goodsOrderService.editGoodsOrder(id, expected);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldDeleteOrderById() {
//        Long id = 10L;
//
//        goodsOrderService.deleteGoodsOrderById(id);
//
//        verify(goodsOrderRepositoryMock).deleteById(id);
//    }
//
//    @Test
//    void shouldGetAllOrders() {
//        List<GoodsOrderDTO> expected = new ArrayList<>();
//        when(goodsOrderRepositoryMock.findAll()).thenReturn(new ArrayList<>());
//
//        List<GoodsOrderDTO> actual = goodsOrderService.getAllGoodsOrders();
//
//        assertEquals(expected, actual);
//        verify(goodsOrderRepositoryMock).findAll();
//    }
}
