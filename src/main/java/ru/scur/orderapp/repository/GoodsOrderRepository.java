package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.GoodsOrder;

public interface GoodsOrderRepository extends JpaRepository<GoodsOrder, Long> {
}
