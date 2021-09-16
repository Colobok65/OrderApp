import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'OrderApp';
  items: MenuItem[] = [];

  ngOnInit(): void {
    this.items = [
      {label: 'Order', routerLink: ['order']},
      {label: 'Goods', routerLink: ['goods']}
      ];
  }
}
