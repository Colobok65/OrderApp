package ru.scur.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scur.orderapp.model.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
