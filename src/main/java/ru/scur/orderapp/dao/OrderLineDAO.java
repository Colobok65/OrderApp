package ru.scur.orderapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;

public interface OrderLineDAO extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findOrderLineByGoodsOrderId(Long goodsOrderId);
    @Query("select g.price from OrderLine o join Goods g on o.id = g.id where o.goods = :id")
    Float findGoodsPrice(@Param("id") Long id);
}
