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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';
import {RippleModule} from 'primeng/ripple';
import {AddGoodsComponent} from './components/add-goods/add-goods.component';
import {MenuModule} from 'primeng/menu';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MenubarModule} from 'primeng/menubar';
import {authInterceptorProviders} from './services/security/auth-interceptor.service';
import {DialogModule} from 'primeng/dialog';
import {authErrorInterceptorProvider} from './services/security/error-interceptor.service';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { UserComponent } from './components/user/user.component';
import {AutoCompleteModule} from 'primeng/autocomplete';

@NgModule({
  declarations: [
    AppComponent,
    GoodsOrderComponent,
    GoodsComponent,
    OrderLineComponent,
    AddGoodsComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
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
    BrowserAnimationsModule,
    MenubarModule,
    DialogModule,
    ReactiveFormsModule,
    AutoCompleteModule
  ],
  providers: [authInterceptorProviders, authErrorInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
