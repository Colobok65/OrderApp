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

  onRowEditSave(goods: GoodsDTO): Subscription {
    if (goods.price > 0) {
      delete this.clonedGoods[goods.id];
    }
    return this.goodsService.saveGoods(goods).subscribe(
      () => this.router.navigate(['productlist'])
    );

  }

  onRowEditCancel(goods: GoodsDTO, index: number): void {
    this.goodses[index] = this.clonedGoods[goods.id];
    delete this.clonedGoods[goods.id];
  }

  showEdit(): void{
    this.isEdit = !this.isEdit;
  }
}
