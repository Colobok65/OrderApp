package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findOrderLineByGoodsOrderId(Long goodsOrderId);
    List<OrderLine> findOrderLineByGoods_Id(Long goodsId);
}
