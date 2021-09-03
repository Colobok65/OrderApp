import {Component, OnInit} from '@angular/core';
import {OrderLineService} from '../../services/order-line/order-line.service';
import {OrderLine} from '../../models/OrderLine';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-order-line',
  templateUrl: './order-line.component.html',
  styleUrls: ['./order-line.component.css']
})
export class OrderLineComponent implements OnInit {

  orderId = +(this.activatedRoute.snapshot.paramMap.get('id') as string);
  goodsPrice = 0;
  isDataLoaded = false;
  lines: OrderLine[] = [];
  line: OrderLine = {id: 0, orderId: 0, goodsId: 0, countNumber: 0, goodsName: ''};
  clonedLines: { [s: string]: OrderLine; } = {};

  constructor(private orderLineService: OrderLineService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getLinesForCurrentOrder();
  }

  getGoodsPrice(id: number): void {
    this.orderLineService.getGoodsPriceFromGoodsId(id)
      .subscribe(data => this.goodsPrice = data);
  }

  getLinesForCurrentOrder(): void {
    this.orderLineService.getLinesByOrderId(this.orderId)
      .subscribe(data => {
        this.lines = data;
        this.isDataLoaded = true;
      });
  }

  deleteLine(id: number): void {
    const result = confirm('Вы действительно хотите удалить этот товар?');
    if (result) {
      this.orderLineService.deleteLineById(id).subscribe(() => this.getLinesForCurrentOrder());
    }
  }

  updateLine(line: OrderLine): void {
    this.orderLineService.updateLineById(line.id || 0, line)
      .subscribe(() => this.getLinesForCurrentOrder());
  }

  onRowEditInit(line: OrderLine): void {
    this.clonedLines[line.id || 0] = {...line};
  }

  onRowEditSave(line: OrderLine): void {
    this.updateLine(line);
    delete this.clonedLines[line.id || 0];
  }

  onRowEditCancel(line: OrderLine, index: number): void {
    this.lines[index] = this.clonedLines[line.id || 0];
    delete this.clonedLines[line.id || 0];
  }
}
