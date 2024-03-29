import {Component, OnInit} from '@angular/core';
import {OrderLine} from '../../models/OrderLine';
import {OrderLineService} from '../../services/order-line/order-line.service';
import {GoodsService} from '../../services/goods/goods.service';
import {Goods} from '../../models/Goods';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-add-goods',
  templateUrl: './add-goods.component.html',
  styleUrls: ['./add-goods.component.css']
})
export class AddGoodsComponent implements OnInit {

  isDataLoaded = false;
  lines: OrderLine[] = [];
  linesFromCurrentOrder: OrderLine[] = [];
  products: Goods[] = [];
  orderId = +(this.activatedRoute.snapshot.paramMap.get('id') as string);


  constructor(private orderLineService: OrderLineService,
              private goodsService: GoodsService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit(): Promise<void> {
    await this.getAllGoods();
    await this.getLinesByCurrentOrder();
    this.initLines();
  }

  async getAllGoods(): Promise<void> {
    this.products = await this.goodsService.getAllGoods().toPromise();
    this.isDataLoaded = true;
  }

  private initLines(): void {
    const orderedGoodsIds = this.linesFromCurrentOrder.map(it => it.goodsId);
    this.lines = this.products
      .filter(g => !orderedGoodsIds.includes(g.id || 0))
      .map(g => ({
      orderId: this.orderId,
      goodsId: g.id || 0,
      countNumber: 0,
      goodsName: g.name,
      price: g.price
    }));
  }

  addLines(): void {
    this.lines
      .filter(line => !!line.countNumber)
      .forEach((line: any) => {
          this.orderLineService.createNewLine({
            countNumber: line.countNumber,
            goodsId: line.goodsId,
            orderId: this.orderId,
            goodsName: line.goodsName,
            price: line.price
          }).subscribe(data => {
            this.router.navigate(['line/order/' + this.orderId]);
            this.isDataLoaded = false;
          });
      });
  }

  async getLinesByCurrentOrder(): Promise<void> {
      this.linesFromCurrentOrder = await this.orderLineService
        .getLinesByOrderId(this.orderId).toPromise();
  }
}
