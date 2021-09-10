import {Component, OnInit} from '@angular/core';
import {OrderLine} from '../../models/OrderLine';
import {OrderLineService} from '../../services/order-line/order-line.service';
import {GoodsService} from '../../services/goods/goods.service';
import {Goods} from '../../models/Goods';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-goods',
  templateUrl: './add-goods.component.html',
  styleUrls: ['./add-goods.component.css']
})
export class AddGoodsComponent implements OnInit {

  orderId = +(this.activatedRoute.snapshot.paramMap.get('id') as string);
  isDataLoaded = false;
  lines: OrderLine[] = [];
  goodses: Goods[] = [];
  totalPrice = 0;

  constructor(private orderLineService: OrderLineService,
              private goodsService: GoodsService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllGoods();
  }

  addGoods(line: OrderLine): void {
    this.orderLineService
      .createNewLine({
        countNumber: line.countNumber,
        goodsId: line.id || 0,
        orderId: this.orderId,
        goodsName: name,
        price: line.price })
      .subscribe(data => this.lines = data);
  }

  getAllGoods(): void {
    this.goodsService.getAllGoods()
      .subscribe(data => {
        this.goodses = data;
        this.isDataLoaded = true;
      });
  }

}
