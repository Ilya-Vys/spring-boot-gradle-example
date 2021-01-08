import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import { GoodsListComponent } from './goods-list/goods-list.component';
import { AppRoutingModule } from './app-routing.module';
import {GoodsService} from "./goods-service.service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    GoodsListComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,

  ],
  providers: [GoodsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
