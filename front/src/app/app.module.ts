import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GoodsOrderComponent } from './goods-order/goods-order.component';
import { GoodsComponent } from './goods/goods.component';
import { HttpClientModule } from '@angular/common/http';
import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import { OrderLineComponent } from './order-line/order-line.component';

@NgModule({
  declarations: [
    AppComponent,
    GoodsOrderComponent,
    GoodsComponent,
    OrderLineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
