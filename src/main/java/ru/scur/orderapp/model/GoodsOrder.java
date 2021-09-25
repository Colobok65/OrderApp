package ru.scur.orderapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "client")
    private String client;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "goodsOrder")
    private List<OrderLine> orderLines = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
