import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GoodsOrderComponent } from './components/goods-order/goods-order.component';
import { GoodsComponent } from './components/goods/goods.component';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { OrderLineComponent } from './components/order-line/order-line.component';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import {RippleModule} from 'primeng/ripple';

@NgModule({
  declarations: [
    AppComponent,
    GoodsOrderComponent,
    GoodsComponent,
    OrderLineComponent,
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
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
