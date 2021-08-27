import { Component, OnInit } from '@angular/core';
import {GoodsOrderDTO, GoodsOrderService} from '../services/goods-order/goods-order.service';

@Component({
  selector: 'app-goods-order',
  templateUrl: './goods-order.component.html',
  styleUrls: ['./goods-order.component.css']
})
export class GoodsOrderComponent implements OnInit {

  goodsOrders: GoodsOrderDTO[] = [];

  constructor(private goodsOrderService: GoodsOrderService) {
  }

  ngOnInit(): void {
  }

}
