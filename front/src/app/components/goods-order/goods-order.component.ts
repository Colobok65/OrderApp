import {Component, OnInit} from '@angular/core';
import {GoodsOrderService} from '../../services/goods-order/goods-order.service';
import {GoodsOrder} from '../../models/GoodsOrder';
import {Router} from '@angular/router';
import {UserService} from '../../services/user/user.service';
import {TokenStorageService} from '../../services/security/token-storage.service';

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
  userId = this.getUserIdFromToken();
  order: GoodsOrder = {id: 0, address: '', date: '', client: '', lines: []};
  clonedOrders: { [s: string]: GoodsOrder; } = {};

  constructor(private goodsOrderService: GoodsOrderService,
              private router: Router,
              private userService: UserService,
              private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.getAllOrdersByUserId();
  }

  addOrder(): void {
    this.goodsOrderService.createOrder(this.order).subscribe(() => {
      this.order.address = '';
      this.order.client = '';
      this.getAllOrdersByUserId();
      this.isPushedButton = false;
      this.isActivatedForm = false;
    });
  }

  getUserIdFromToken(): number {
    return this.tokenService.getUserIdFromToken().id;
  }

  getAllOrdersByUserId(): void {
    this.goodsOrderService.getOrdersByUserId(this.userId)
      .subscribe(data => {
        this.orders = data;
        this.isDataLoaded = true;
    });
  }

  deleteOrder(id: number): void {
    const result = confirm('Вы действительно хотите удалить этот заказ?');
    if (result) {
      this.goodsOrderService.deleteOrder(id).subscribe(() => this.getAllOrdersByUserId());
    }
  }

  updateOrder(order: GoodsOrder): void {
    this.goodsOrderService.updateOrder(order.id || 0, order)
      .subscribe(() => this.getAllOrdersByUserId());
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
    this.router.navigate(['order/goods', id]);
  }

}
