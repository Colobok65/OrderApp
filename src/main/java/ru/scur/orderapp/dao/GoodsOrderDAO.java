package ru.scur.orderapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.GoodsOrder;

public interface GoodsOrderDAO extends JpaRepository<GoodsOrder, Long> {
}
