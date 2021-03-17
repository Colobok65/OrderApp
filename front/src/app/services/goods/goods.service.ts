import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {tap} from 'rxjs/operators';

export class GoodsDTO {
    public id!: number;
    public name!: string;
    public price!: number;
}

@Injectable({
  providedIn: 'root'
})
export class GoodsService {

  private endpoint = `${environment.apiUrl}/order_app/goods`;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) { }

  getAllGoods(): Observable<GoodsDTO[]> {
    return this.httpClient.get<GoodsDTO[]>(this.endpoint);
  }

  getGoodsById(id: number): Observable<GoodsDTO> {
    return this.httpClient.get<GoodsDTO>(this.endpoint + '/' + id);
  }

  saveGoods(goods: GoodsDTO): Observable<GoodsDTO> {
    return  this.httpClient.post<GoodsDTO>(this.endpoint, goods);
  }

  // getGoodsById(): Observable<GoodsDTO> {
  //   return this.httpClient.get<GoodsDTO>(`${this.endpoint}/2`);
  // }
  //
  // editGoods(goodsDTO: GoodsDTO): Observable<any> {
  //   return this.httpClient.put(`${this.endpoint}/1`, goodsDTO.name = 'Shugar', this.httpOptions);
  // }
  //
  // deleteGoodsById(): Observable<GoodsDTO> {
  //   const url = `${this.endpoint}/2`;
  //   return this.httpClient.delete<GoodsDTO>(url, this.httpOptions);
  // }
}

