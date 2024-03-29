import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OrderLine} from '../../models/OrderLine';

const LINE_API = 'http://localhost:8080/order_app/line';

@Injectable({
  providedIn: 'root'
})
export class OrderLineService {
  constructor(private http: HttpClient) { }

  getLinesByOrderId(id: number): Observable<any> {
    return this.http.get(LINE_API + '/order/' + id);
  }

  deleteLineById(id: number): Observable<any> {
    return this.http.delete(LINE_API + '/' + id);
  }

  updateLineById(id: number, line: OrderLine): Observable<any> {
    return this.http.put(LINE_API + '/' + id, line);
  }

  createNewLine(line: OrderLine): Observable<any> {
    return this.http.post(LINE_API, line);
  }

}
