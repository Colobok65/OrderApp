package ru.scur.orderapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.mock.MockUserDetailsService;
import ru.scur.orderapp.security.JwtAuthenticationEntryPoint;
import ru.scur.orderapp.security.JwtTokenProvider;
import ru.scur.orderapp.service.GoodsOrderService;
import ru.scur.orderapp.util.GoodsOrderDTOListUtil;
import ru.scur.orderapp.util.GoodsOrderDTOUtil;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GoodsOrderController.class)
@Import({JwtTokenProvider.class,
        MockUserDetailsService.class,
        JwtAuthenticationEntryPoint.class})
@WithMockUser(username = "username", authorities = {"ADMIN", "USER"})
public class GoodsOrderControllerTest {

    @Autowired
    GoodsOrderController goodsOrderController;

    @MockBean
    private GoodsOrderService goodsOrderService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturnGoodsOrderById() throws Exception {
        GoodsOrderDTO goodsOrderDTO = GoodsOrderDTOUtil.getGoodsOrderDTO();
        when(goodsOrderService.getGoodsOrderById(any())).thenReturn(goodsOrderDTO);
        this.mockMvc
                .perform(get("/order_app/order/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.client").value("username"))
                .andExpect(jsonPath("$.address").value("address"))
                .andExpect(jsonPath("$.userId").value("1"));

        verify(goodsOrderService).getGoodsOrderById(1L);
    }

    @Test
    public void shouldReturnAllGoodsOrder() throws Exception{
        List<GoodsOrderDTO> expected = GoodsOrderDTOListUtil.getGoodsOrderDtoList();
        when(goodsOrderService.getAllGoodsOrders()).thenReturn(expected);
        this.mockMvc
                .perform(get("/order_app/order"))
                .andDo(print())
                .andExpect(status().isOk())
                /*проверяем размер возвращаемого списка*/
                .andExpect(jsonPath("$", hasSize(1)))
                /*проверяем что возвращается заказ с id равным 1(единице)*/
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1)))
                .andExpect(jsonPath("$[*].client", containsInAnyOrder("username")))
                .andExpect(jsonPath("$[*].address", containsInAnyOrder("address")))
                .andExpect(jsonPath("$[*].userId", containsInAnyOrder(1)));

        verify(goodsOrderService).getAllGoodsOrders();
    }

    @Test
    public void shouldCreateGoodsOrder() throws Exception{
        GoodsOrderDTO goodsOrderDTO = GoodsOrderDTOUtil.getGoodsOrderDTO();
        when(goodsOrderService.createGoodsOrder(any())).thenReturn(goodsOrderDTO);
        this.mockMvc.perform(post("/order_app/order")
                .content(mapper.writeValueAsString(goodsOrderDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.client").value("username"))
//                .andExpect(jsonPath("$.address").value("address"));
//                .andExpect(jsonPath("$.address", equalTo("address")));

//        verify(goodsOrderService).createGoodsOrder(goodsOrderDTO);
    }

    @Test
    public void shouldDeleteGoodsOrderById() throws Exception{
        mockMvc.perform(delete("/order_app/order/1"))
                .andExpect(status().isOk());

        verify(goodsOrderService).deleteGoodsOrderById(1L);
    }

    @Test
    public void shouldEditGoodsOrderById() throws Exception{
        GoodsOrderDTO goodsOrderDTO = GoodsOrderDTOUtil.getGoodsOrderDTO();
        goodsOrderDTO.setClient("client 2");
        when(goodsOrderService.editGoodsOrder(any())).thenReturn(goodsOrderDTO);
        mockMvc.perform(put("/order_app/order/1")
                .content(mapper.writeValueAsString(goodsOrderDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.client").value("client 2"));

        verify(goodsOrderService).editGoodsOrder(1L);
    }
}
