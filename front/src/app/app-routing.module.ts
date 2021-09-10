import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GoodsComponent} from './components/goods/goods.component';
import {GoodsOrderComponent} from './components/goods-order/goods-order.component';
import {OrderLineComponent} from './components/order-line/order-line.component';
import {AddGoodsComponent} from './components/add-goods/add-goods.component';

const routes: Routes = [
  { path: 'goods', component: GoodsComponent },
  { path: 'order', component: GoodsOrderComponent},
  { path: 'line', component: OrderLineComponent },
  { path: 'line/order/:id', component: OrderLineComponent, children: [
      { path: '', component: AddGoodsComponent }
    ] },
  { path: '', redirectTo: 'order', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
