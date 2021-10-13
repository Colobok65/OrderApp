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
  orders: GoodsOrder[] = [];
  userId = this.getUserIdFromToken();
  order: GoodsOrder = {id: 0, address: '', date: '', client: '', lines: [], userId: this.userId};

  constructor(private goodsOrderService: GoodsOrderService,
              private router: Router,
              private userService: UserService,
              private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.getAllOrdersByUserId();
  }

  getUserIdFromToken(): number {
    return this.tokenService.getObjectFromToken().id;
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

  addNewOrder(order: GoodsOrder): void {
    this.goodsOrderService.createOrder(order)
      .subscribe(() => this.getAllOrdersByUserId());
  }

  showGoods(id: number): void {
    this.router.navigate(['line/order/', id]);
  }

}
