import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GoodsComponent} from './components/goods/goods.component';
import {GoodsOrderComponent} from './components/goods-order/goods-order.component';
import {OrderLineComponent} from './components/order-line/order-line.component';

const routes: Routes = [
  { path: 'goods', component: GoodsComponent },
  { path: 'order', component: GoodsOrderComponent},
  { path: 'line', component: OrderLineComponent },
  { path: 'line/:id', component: OrderLineComponent },
  { path: '', redirectTo: 'goods', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
