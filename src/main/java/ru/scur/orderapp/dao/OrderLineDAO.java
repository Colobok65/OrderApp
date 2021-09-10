package ru.scur.orderapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;

public interface OrderLineDAO extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findOrderLineByGoodsOrderId(Long goodsOrderId);

}
