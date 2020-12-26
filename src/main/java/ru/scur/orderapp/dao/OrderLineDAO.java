package ru.scur.orderapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.OrderLine;

public interface OrderLineDAO extends JpaRepository<OrderLine, Long> {
}
