import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const LINE_API = 'http://localhost:9000/order_app/line';

@Injectable({
  providedIn: 'root'
})
export class OrderLineService {
  constructor(private http: HttpClient) { }

  getAllLines(): Observable<any> {
    return this.http.get(LINE_API);
  }

  getLineById(id: number): Observable<any> {
    return this.http.get(LINE_API + '/' + id);
  }
}


