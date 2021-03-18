import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import DateTimeFormat = Intl.DateTimeFormat;

export class OrderLineDTO {
  public id!: number;
  public orderId!: number;
  public goodsId!: number;
  public goodsName!: string;
  public count!: number;
}

export class GoodsOrderDTO {
    public id!: number;
    public client!: string;
    public date = new Date();
    public address!: string;
    public orderLines!: OrderLineDTO[];
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

  editGoodsOrder(goodsOrder: GoodsOrderDTO): Observable<GoodsOrderDTO> {
    return  this.httpClient.put<GoodsOrderDTO>(`${this.endpoint}/${goodsOrder.id}`, goodsOrder);
  }

  deleteGoodsById(id: number): Observable<GoodsOrderDTO> {
    return this.httpClient.delete<GoodsOrderDTO>(this.endpoint + '/' + id);
  }

  saveGoodsOrder(goodsOrder: GoodsOrderDTO): Observable<GoodsOrderDTO> {
    return this.httpClient.post<GoodsOrderDTO>(this.endpoint, goodsOrder);
  }
}
