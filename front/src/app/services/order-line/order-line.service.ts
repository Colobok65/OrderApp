import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

export class OrderLineDTO {
  private id!: number;
  private orderId!: number;
  private goodsId!: number;
  private goodsName!: string;
  private count!: number;
}

@Injectable({
  providedIn: 'root'
})
export class OrderLineService {

  private endpoint = `${environment.apiUrl}/order_app/line`;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) { }

  getAllOrderLines(): Observable<OrderLineDTO[]> {
    return this.httpClient.get<OrderLineDTO[]>(this.endpoint);
  }
}


