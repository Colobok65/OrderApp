package ru.scur.orderapp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.GoodsOrder;

public interface OrderDAO extends JpaRepository<GoodsOrder, Long> {
}
