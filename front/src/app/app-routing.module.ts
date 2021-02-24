import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GoodsComponent} from './goods/goods.component';
import {GoodsOrderComponent} from './goods-order/goods-order.component';
import {OrderLineComponent} from './order-line/order-line.component';

const routes: Routes = [
  { path: 'order_app/goods', component: GoodsComponent },
  { path: 'order_app/order', component: GoodsOrderComponent },
  { path: 'order_app/line', component: OrderLineComponent },
  { path: '', redirectTo: 'order_app/order', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
