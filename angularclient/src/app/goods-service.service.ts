import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Goods } from '../entities/goods';
import { Observable } from 'rxjs';
import {Goods} from "./goods";

@Injectable()
export class GoodsService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api/goods/';
  }

  public findAll(): Observable<Goods[]> {
    return this.http.get<Goods[]>(this.usersUrl);
  }

  public save(goods: Goods) {
    return this.http.post<Goods>(this.usersUrl, goods);
  }

}
