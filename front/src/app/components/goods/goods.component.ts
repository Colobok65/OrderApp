import {Component, OnInit} from '@angular/core';
import {GoodsService} from '../../services/goods/goods.service';
import {Goods} from '../../models/Goods';

@Component({
  selector: 'app-goods',
  templateUrl: './goods.component.html',
  styleUrls: ['./goods.component.css']
})
export class GoodsComponent implements OnInit {

  isDataLoaded = false;
  goodses: Goods[] = [];
  goods: Goods = {id: 0, name: '', price: 0};
  clonedGoods: { [s: string]: Goods; } = {};

  constructor(private goodsService: GoodsService) {}

  ngOnInit(): void {
    this.getAllGoods();
  }

  getAllGoods(): void {
    this.goodsService.getAllGoods()
      .subscribe(
        data => {
          this.goodses = data;
          this.isDataLoaded = true;
        }
      );
  }

  addGoods(): void {
    this.goodsService.addGoods(this.goods).subscribe(() => {
      this.goods.name = '';
      this.goods.price = 0;
      this.getAllGoods();
      this.goodsFormNone();
      this.goodsButtonBlock();
    });
  }

  deleteGoods(id: number): void {
    const result = confirm('Вы действительно хотите удалить этот товар?');
    if (result) {
      this.goodsService.deleteGoods(id).subscribe(() => this.getAllGoods());
    }
  }

  updateGoods(goods: Goods): void {
    this.goodsService.updateGoods(goods.id || 0, goods)
      .subscribe(() => this.getAllGoods());
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
    document.getElementById('addGoodsForm').style.display = 'block';
  }

  goodsFormNone(): void{
    // @ts-ignore
    document.getElementById('addGoodsForm').style.display = 'none';
  }

  goodsButtonBlock(): void{
    document.getElementById('addGoodsButton').style.display = 'block';
  }

  goodsButtonNone(): void {
    // @ts-ignore
    document.getElementById('addGoodsButton').style.display = 'none';
  }
}
