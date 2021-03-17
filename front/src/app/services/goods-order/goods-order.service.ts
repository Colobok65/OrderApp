import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

export class OrderLineDTO {
  constructor(
    private id: number,
    private orderId: number,
    private goodsId: number,
    private count: number,
  ) {
  }
}

export class GoodsOrderDTO {
  constructor(
    public id: number,
    public client: string,
    public date: Date = new Date(),
    public address: string,
    public orderLines: OrderLineDTO[],
  ) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class GoodsOrderService {

  private endpoint = `${environment.apiUrl}/order_app/order`;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) { }

  getAllGoodsOrders(): Observable<GoodsOrderDTO[]> {
    return this.httpClient.get<GoodsOrderDTO[]>(this.endpoint);
  }

  // getGoodsOrderById(): Observable<GoodsOrderDTO> {
  //   return this.httpClient.get<GoodsOrderDTO>(`${this.endpoint}/1`);
  // }
  //
  // editGoodsOrder(updated: GoodsOrderDTO): Observable<any> {
  //   updated.address = 'updated address';
  //   updated.client = 'updated client';
  //   return this.httpClient.put(`${this.endpoint}/3`, updated, this.httpOptions);
  // }
  //
  // deleteGoodsOrderByID(): Observable<GoodsOrderDTO> {
  //   const url = `${this.endpoint}/3`;
  //   return this.httpClient.delete<GoodsOrderDTO>(url, this.httpOptions);
  // }
}
