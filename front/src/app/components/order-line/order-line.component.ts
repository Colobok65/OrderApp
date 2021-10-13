import {Component, OnInit} from '@angular/core';
import {OrderLineService} from '../../services/order-line/order-line.service';
import {OrderLine} from '../../models/OrderLine';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-order-line',
  templateUrl: './order-line.component.html',
  styleUrls: ['./order-line.component.css']
})
export class OrderLineComponent implements OnInit {

  orderId = +(this.activatedRoute.snapshot.paramMap.get('id') as string);
  isDataLoaded = false;
  isButtonPushed = false;
  isPanelActivated = false;
  lines: OrderLine[] = [];
  line: OrderLine = {id: 0, orderId: 0, goodsId: 0, countNumber: 0, goodsName: '', price: 0};
  clonedLines: { [s: string]: OrderLine; } = {};
  totalSum = 0;

  constructor(private orderLineService: OrderLineService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.getLinesForCurrentOrder();
  }

  getLinesForCurrentOrder(): void {
    this.orderLineService.getLinesByOrderId(this.orderId)
      .subscribe(data => {
        this.lines = data;
        this.totalSum = data.reduce((total: any, a: { price: any; countNumber: any}) => total + a.price * a.countNumber, 0);
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

  addGoods(): void {
    this.router.navigate(['line/order/add/' + this.orderId]);
    this.isPanelActivated = true;
    this.isButtonPushed = true;
  }
}
