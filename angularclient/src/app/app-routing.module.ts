import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GoodsListComponent } from './goods-list/goods-list.component';
import {GoodsFormComponent} from "./goods-form/goods-form.component";

const routes: Routes = [
  { path: 'goods', component: GoodsListComponent },
  { path: 'addgoods', component: GoodsFormComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
