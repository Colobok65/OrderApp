import { Component, OnInit } from '@angular/core';
import {OrderLineDTO, OrderLineService} from '../../services/order-line/order-line.service';

@Component({
  selector: 'app-order-line',
  templateUrl: './order-line.component.html',
  styleUrls: ['./order-line.component.css']
})
export class OrderLineComponent implements OnInit {

  orderLines: OrderLineDTO[] = [];

  constructor(private orderLineService: OrderLineService) { }

  ngOnInit(): void {
    this.getAllOrderLines();
  }

  getAllOrderLines(): void {
    this.orderLineService.getAllOrderLines()
      .subscribe(result => this.orderLines = result);
  }

}
