import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Goods} from '../../models/Goods';

const GOODS_API = 'http://localhost:8080/order_app/goods';

@Injectable({
  providedIn: 'root'
})
export class GoodsService {

  constructor(private http: HttpClient) { }

  getAllGoods(): Observable<any> {
    return this.http.get(GOODS_API);
  }

  addGoods(goods: Goods): Observable<any> {
    return this.http.post(GOODS_API, goods);
  }

  deleteGoods(id: number): Observable<any> {
    return this.http.delete(GOODS_API + '/' + id);
  }

  updateGoods(id: number, goods: Goods): Observable<any> {
    return this.http.put(GOODS_API + '/' + id, goods);
  }

}

