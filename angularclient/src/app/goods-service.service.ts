import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Goods } from '../entities/goods';
import { Observable } from 'rxjs';
import {Goods} from "./goods";

@Injectable()
export class GoodsService {

  private goodsUrl: string;

  constructor(private http: HttpClient) {
    this.goodsUrl = 'http://localhost:8080/api/goods/';
  }

  public findAll(): Observable<Goods[]> {
    return this.http.get<Goods[]>(this.goodsUrl);
  }

  public save(goods: Goods) {
    return this.http.post<Goods>(this.goodsUrl, goods);
  }

  public delete(id: string){
    return this.http.delete(this.goodsUrl + '/' + id);
  }

}
