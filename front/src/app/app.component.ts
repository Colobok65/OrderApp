import {Component, OnInit} from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'OrderApp';

  items: MenuItem[] = [];

  constructor(private primengConfig: PrimeNGConfig) {}

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.items = [
      {label: 'Заказы', icon: 'pi pi-folder-open', routerLink: ['order']},
      {label: 'Товары', icon: 'pi pi-folder-open', routerLink: ['goods']}
    ];
  }
}
