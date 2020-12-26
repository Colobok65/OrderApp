package ru.scur.orderapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.Goods;

public interface GoodsDAO extends JpaRepository<Goods, Long> {
}
