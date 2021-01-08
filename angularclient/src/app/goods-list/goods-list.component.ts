import { Component, OnInit } from '@angular/core';
import {Goods} from "../goods";
import {GoodsService} from "../goods-service.service";

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {

  goods: Goods[];

  constructor(private goodsService: GoodsService) { }

  ngOnInit() {
    this.goodsService.findAll().subscribe(data => {
      this.goods = data;
    });
  }
}
