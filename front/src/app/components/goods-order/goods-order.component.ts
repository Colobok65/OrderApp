import { Component, OnInit } from '@angular/core';
import {GoodsOrderService} from '../../services/goods-order/goods-order.service';
import {GoodsOrder} from '../../models/GoodsOrder';
import {Router} from '@angular/router';

@Component({
  selector: 'app-goods-order',
  templateUrl: './goods-order.component.html',
  styleUrls: ['./goods-order.component.css']
})
export class GoodsOrderComponent implements OnInit {

  isDataLoaded = false;
  isPushedButton = false;
  isActivatedForm = false;
  orders: GoodsOrder[] = [];
  order: GoodsOrder = {id: 0, address: '', date: '', client: '', lines: []};
  clonedOrders: { [s: string]: GoodsOrder; } = {};

  constructor(private goodsOrderService: GoodsOrderService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders(): void {
    this.goodsOrderService.getAllOrders().subscribe(data => {
      this.orders = data;
      this.isDataLoaded = true;
    });
  }

  addOrder(): void {
    this.goodsOrderService.createOrder(this.order).subscribe(() => {
      this.order.address = '';
      this.order.client = '';
      this.getAllOrders();
      this.isPushedButton = false;
      this.isActivatedForm = false;
    });
  }

  deleteOrder(id: number): void {
    const result = confirm('Вы действительно хотите удалить этот заказ?');
    if (result) {
      this.goodsOrderService.deleteOrder(id).subscribe(() => this.getAllOrders());
    }
  }

  updateOrder(order: GoodsOrder): void {
    this.goodsOrderService.updateOrder(order.id || 0, order)
      .subscribe(() => this.getAllOrders());
  }

  onRowEditInit(order: GoodsOrder): void {
    this.clonedOrders[order.id || 0] = {...order};
  }

  onRowEditSave(order: GoodsOrder): void {
    this.updateOrder(order);
    delete this.clonedOrders[order.id || 0];
  }

  onRowEditCancel(order: GoodsOrder, index: number): void {
    this.orders[index] = this.clonedOrders[order.id || 0];
    delete this.clonedOrders[order.id || 0];
  }

  addNewOrder(): void {
    this.isPushedButton = true;
    this.isActivatedForm = true;
  }

  cancel(): void {
    this.isPushedButton = false;
    this.isActivatedForm = false;
  }

  showGoods(id: number): void {
    this.router.navigate(['/line/order', id]);
  }

}
