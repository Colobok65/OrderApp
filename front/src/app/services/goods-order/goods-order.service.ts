import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GoodsOrder} from '../../models/GoodsOrder';

const ORDER_API = 'http://localhost:8080/order_app/order';

@Injectable({
  providedIn: 'root'
})
export class GoodsOrderService {

  constructor(private http: HttpClient) { }

  createOrder(order: GoodsOrder): Observable<any> {
    return this.http.post(ORDER_API, order);
  }

  getAllOrders(): Observable<any> {
    return this.http.get(ORDER_API);
  }

  deleteOrder(id: number): Observable<any> {
    return this.http.delete(ORDER_API + '/' + id);
  }

  updateOrder(id: number, order: GoodsOrder): Observable<any> {
    return this.http.put(ORDER_API + '/' + id, order);
  }

  getOrdersByUserId(id: number): Observable<any> {
    return this.http.get(ORDER_API + '/user/' + id);
  }
}
