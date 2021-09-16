import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GoodsOrderComponent} from './components/goods-order/goods-order.component';
import {GoodsComponent} from './components/goods/goods.component';
import {HttpClientModule} from '@angular/common/http';
import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {OrderLineComponent} from './components/order-line/order-line.component';
import {FormsModule} from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';
import {RippleModule} from 'primeng/ripple';
import {AddGoodsComponent} from './components/add-goods/add-goods.component';
import {MenuModule} from 'primeng/menu';

@NgModule({
  declarations: [
    AppComponent,
    GoodsOrderComponent,
    GoodsComponent,
    OrderLineComponent,
    AddGoodsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    FormsModule,
    InputTextModule,
    RippleModule,
    MenuModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
