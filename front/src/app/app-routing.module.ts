import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GoodsComponent} from './components/goods/goods.component';
import {GoodsOrderComponent} from './components/goods-order/goods-order.component';
import {OrderLineComponent} from './components/order-line/order-line.component';
import {LoginComponent} from './components/auth/login/login.component';
import {RegisterComponent} from './components/auth/register/register.component';
import {AuthGuardService} from './services/security/auth-guard.service';
import {UserComponent} from './components/user/user.component';
import {AddGoodsComponent} from './components/add-goods/add-goods.component';

const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'user', component: UserComponent, canActivate: [AuthGuardService]},
  { path: 'order', component: GoodsOrderComponent, canActivate: [AuthGuardService]},
  { path: 'goods', component: GoodsComponent, canActivate: [AuthGuardService] },
  { path: 'line/order/:id', component: OrderLineComponent, canActivate: [AuthGuardService]},
  { path: 'line/order/add/:id', component: AddGoodsComponent, canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
