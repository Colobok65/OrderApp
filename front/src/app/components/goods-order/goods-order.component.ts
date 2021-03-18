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
    this.getAllGoodsOrders();
  }

  showEdit(): void {
    this.isEdit = !this.isEdit;
  }

  getAllGoodsOrders(): void {
    this.goodsOrderService.getAllGoodsOrders()
      .subscribe(result => this.goodsOrders = result);
  }

  onRowEditInit(goodsOrder: GoodsOrderDTO): void {
    this.clonedGoodsOrder[goodsOrder.id] = {...goodsOrder};
  }

  onRowEditSave(goodsOrder: GoodsOrderDTO): void {
    this.goodsOrderService.editGoodsOrder(goodsOrder).subscribe(
      () => this.getAllGoodsOrders()
    );
  }

  onRowEditCancel(goodsOrder: GoodsOrderDTO, index: number): void {
    this.goodsOrders[index] = this.clonedGoodsOrder[goodsOrder.id];
    delete this.clonedGoodsOrder[goodsOrder.id];
  }

  deleteGoodsOrderById(id: number): void {
    this.goodsOrderService.deleteGoodsById(id).subscribe(
      () => this.getAllGoodsOrders()
    );
  }

  saveGoods(): void {
    this.goodsOrderService.saveGoodsOrder(this.goodsOrder).subscribe(
      () => this.getAllGoodsOrders()
    );
  }
}
