package ru.scur.orderapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goods_order")
public class GoodsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @OneToMany(mappedBy = "goodsOrder", fetch = FetchType.LAZY)
    private List<OrderLine> orderLines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
