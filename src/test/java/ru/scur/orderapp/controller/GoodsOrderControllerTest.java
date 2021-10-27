package ru.scur.orderapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.scur.orderapp.service.GoodsOrderService;

@WebMvcTest(GoodsOrderController.class)
public class GoodsOrderControllerTest {

    @Autowired
    GoodsOrderController goodsOrderController;

    @MockBean
    private GoodsOrderService goodsOrderService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

//    @Test
//    public void shouldReturnGoodsOrderById() throws Exception {
//        GoodsOrderDTO goodsOrderDTO = GoodsOrderDTOUtil.getGoodsOrderDTO();
//        when(goodsOrderService.getGoodsOrderById(any())).thenReturn(goodsOrderDTO);
//        this.mockMvc
//                .perform(get("/order_app/order/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.client").value("client"));
//
//        verify(goodsOrderService).getGoodsOrderById(1L);
//    }
//
//    @Test
//    public void shouldReturnAllGoodsOrder() throws Exception{
//        List<GoodsOrderDTO> expected = new ArrayList<>();
//        expected.add(GoodsOrderDTOUtil.getGoodsOrderDTO());
//        when(goodsOrderService.getAllGoodsOrders()).thenReturn(expected);
//        String result = this.mockMvc
//                .perform(get("/order_app/order"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        List<GoodsOrderDTO> actual = mapper.readValue(result, new TypeReference<List<GoodsOrderDTO>>() {});
//
//        assertEquals(expected.get(0), actual.get(0));
//
//        verify(goodsOrderService).getAllGoodsOrders();
//    }
//
//    @Test
//    public void shouldCreateGoodsOrder() throws Exception{
//        GoodsOrderDTO goodsOrderDTO = GoodsOrderDTOUtil.getGoodsOrderDTO();
//        when(goodsOrderService.createGoodsOrder(any())).thenReturn(goodsOrderDTO);
//        this.mockMvc.perform(post("/order_app/order")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(goodsOrderDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.client").value("client"))
//                .andExpect(jsonPath("$.address").value("address"));
//
//        verify(goodsOrderService).createGoodsOrder(goodsOrderDTO);
//    }

//    @Test
//    public void shouldDeleteGoodsOrderById() throws Exception{
//        mockMvc.perform(delete("/order_app/order/1"))
//                .andExpect(status().isOk());
//
//        verify(goodsOrderService).deleteGoodsOrderById(1L);
//    }
//
//    @Test
//    public void shouldEditGoodsOrderById() throws Exception{
//        GoodsOrderDTO goodsOrderDTO = GoodsOrderDTOUtil.getGoodsOrderDTO();
//        goodsOrderDTO.setClient("client 2");
//        when(goodsOrderService.editGoodsOrder(any(),any())).thenReturn(goodsOrderDTO);
//        mockMvc.perform(put("/order_app/order/1")
//                .content(mapper.writeValueAsString(goodsOrderDTO))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.client").value("client 2"));
//    }
}
