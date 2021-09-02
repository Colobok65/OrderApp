import { Component, OnInit } from '@angular/core';
import {GoodsService, GoodsDTO} from '../services/goods/goods.service';

@Component({
  selector: 'app-goods',
  templateUrl: './goods.component.html',
  styleUrls: ['./goods.component.css']
})
export class GoodsComponent implements OnInit {

  goodses: GoodsDTO[] = [];
  // goods: GoodsDTO;

  constructor(
    private goodsService: GoodsService,
  ) {}

  ngOnInit(): void {
    // this.getGoodsById();
    this.getAllGoods();
    // this.editGoods();
  }

  getAllGoods(): void {
    this.goodsService.getAllGoods()
      .subscribe(result => this.goodses = result);
  }

  onRowEditInit(goods: GoodsDTO): void {
    this.clonedGoods[goods.id] = {...goods};
  }

  onRowEditSave(goods: GoodsDTO): void {
    this.goodsService.editGoods(goods).subscribe(
      () => this.getAllGoods()
    );
  }

  saveGoods(): void {
    this.goodsService.saveGoods(this.goods).subscribe(
      () => this.getAllGoods()
    );
  }

  onRowEditCancel(goods: GoodsDTO, index: number): void {
    this.goodses[index] = this.clonedGoods[goods.id];
    delete this.clonedGoods[goods.id];
  }

  deleteGoodsById(id: number): void {
    this.goodsService.deleteGoodsById(id).subscribe(
      () => this.getAllGoods()
    );
  }

  showAdd(): void {
    this.isAdd = !this.isAdd;
  }

  onRowEditInit(goods: Goods): void {
    this.clonedGoods[goods.id || 0] = {...goods};
  }

  onRowEditSave(goods: Goods): void {
    if (goods.price > 0) {
      this.updateGoods(goods);
      delete this.clonedGoods[goods.id || 0];
    }
  }

  onRowEditCancel(goods: Goods, index: number): void {
    this.goodses[index] = this.clonedGoods[goods.id || 0];
    delete this.clonedGoods[goods.id || 0];
  }

  addNewGoods(): void {
    this.goodsFormBlock();
    this.goodsButtonNone();
  }

  cancel(): void {
    this.goodsFormNone();
    this.goodsButtonBlock();
  }

  goodsFormBlock(): void {
    // @ts-ignore
    document.getElementById('addOrderForm').style.display = 'block';
  }

  goodsFormNone(): void{
    // @ts-ignore
    document.getElementById('addOrderForm').style.display = 'none';
  }

  goodsButtonBlock(): void{
    // @ts-ignore
    document.getElementById('addOrderButton').style.display = 'block';
  }

  goodsButtonNone(): void {
    // @ts-ignore
    document.getElementById('addOrderButton').style.display = 'none';
  }
}
