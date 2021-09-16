import {Component, OnInit} from '@angular/core';
import {OrderLine} from '../../models/OrderLine';
import {OrderLineService} from '../../services/order-line/order-line.service';
import {GoodsService} from '../../services/goods/goods.service';
import {Goods} from '../../models/Goods';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-goods',
  templateUrl: './add-goods.component.html',
  styleUrls: ['./add-goods.component.css']
})
export class AddGoodsComponent implements OnInit {

  // @ts-ignore
  orderId = +(this.router.url.match('\\d')[0]);
  isDataLoaded = false;
  line: OrderLine = {orderId: 0, goodsId: 0, price: 0, goodsName: '', countNumber: 0};
  lines: OrderLine[] = [];
  linesFromCurrentOrder: OrderLine[] = [];
  products: Goods[] = [];
  currentGoodsId: number[] = [];

  constructor(private orderLineService: OrderLineService,
              private goodsService: GoodsService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllGoods();
    this.getLinesByCurrentOrder();
  }

  getAllGoods(): void {
    this.goodsService.getAllGoods()
      .subscribe(data => {
        this.products = data;
        this.initLines();
        this.isDataLoaded = true;
      });
  }

  private initLines(): void {
    this.lines = this.products.map(g => ({
      orderId: this.orderId,
      goodsId: g.id || 0,
      countNumber: 0,
      goodsName: g.name,
      price: g.price
    }));
  }

  addLines(): void {
    this.lines
      .filter(line => !!line.countNumber
      && !this.linesFromCurrentOrder.includes(line))
      .forEach((line: any) => {
        if (!this.lines.includes(this.getLineByGoodsId(line.goodsId))) {
          this.orderLineService.createNewLine({
            countNumber: line.countNumber,
            goodsId: line.goodsId,
            orderId: this.orderId,
            goodsName: line.goodsName,
            price: line.price
          }).subscribe(data => this.lines = data);
        } else {console.log('Этот товар есть в заказе'); }
      });
    console.log(this.linesFromCurrentOrder);
    console.log(this.currentGoodsId);
  }

  getLineByGoodsId(id: number): any {
    return this.orderLineService.getLineByGoodsId(id)
      .subscribe(data => this.line = data);
  }

  getLinesByCurrentOrder(): void {
    this.orderLineService.getLinesByOrderId(this.orderId)
      .subscribe(data => {
        this.linesFromCurrentOrder = data;
        this.currentGoodsId.push(data.goodsId);
      });
  }
}
