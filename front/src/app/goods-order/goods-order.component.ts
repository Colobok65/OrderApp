import { Component, OnInit } from '@angular/core';
import {GoodsOrderDTO, GoodsOrderService} from '../services/goods-order/goods-order.service';

@Component({
  selector: 'app-goods-order',
  templateUrl: './goods-order.component.html',
  styleUrls: ['./goods-order.component.css']
})
export class GoodsOrderComponent implements OnInit {

  goodsOrders: GoodsOrderDTO[] = [];
  goodsOrder: GoodsOrderDTO | undefined;

  constructor(
    private goodsOrderService: GoodsOrderService,
  ) {
  }

  ngOnInit(): void {
    this.getAllGoodsOrders();
    this.getGoodsOrderById();
  }

  editGoodsOrder(): void {
    if (this.goodsOrder) {
      this.goodsOrderService.editGoodsOrder(this.goodsOrder)
        .subscribe(result => this.goodsOrder = result);
    }
  }
  deleteGoodsOrderByID(): void{
    this.goodsOrderService.deleteGoodsOrderByID()
      .subscribe(result => this.goodsOrder = result);
  }
  getGoodsOrderById(): void {
    this.goodsOrderService.getGoodsOrderById()
      .subscribe(result => this.goodsOrder = result);
  }

  getAllGoodsOrders(): void {
    this.goodsOrderService.getAllGoodsOrders()
      .subscribe(result => this.goodsOrders = result);
  }
}
