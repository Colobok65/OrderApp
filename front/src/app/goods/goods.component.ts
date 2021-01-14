import { Component, OnInit } from '@angular/core';
import {GoodsService, GoodsDTO} from '../services/goods/goods.service';

@Component({
  selector: 'app-goods',
  templateUrl: './goods.component.html',
  styleUrls: ['./goods.component.css']
})
export class GoodsComponent implements OnInit {

  goodses: GoodsDTO[] = [];
  goods!: GoodsDTO;

  constructor(
    private goodsService: GoodsService,
  ) {}

  ngOnInit(): void {
    this.getGoodsById();
    this.getAllGoods();
    this.editGoods();
  }

  editGoods(): void {
    this.goodsService.editGoods(this.goods)
      .subscribe(result => this.goods = result);
  }
  deleteGoodsByID(): void{
    this.goodsService.deleteGoodsById()
      .subscribe(result => this.goods = result);
  }
  getGoodsById(): void {
    this.goodsService.getGoodsById()
      .subscribe(result => this.goods = result);
  }

  getAllGoods(): void {
    this.goodsService.getAllGoods()
      .subscribe(result => this.goodses = result);
  }
}
