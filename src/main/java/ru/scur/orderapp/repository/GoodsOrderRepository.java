package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.List;

public interface GoodsOrderRepository extends JpaRepository<GoodsOrder, Long> {
    List<GoodsOrder> findGoodsOrdersByUserId(Long userId);
}
