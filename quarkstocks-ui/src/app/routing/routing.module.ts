import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TickerComponent} from '../ticker/ticker.component';
import {StockComponent} from "../stock/stock.component";


const routes: Routes = [
  { path: 'ticker', component: TickerComponent},
  { path: 'stocks', component: StockComponent},
  { path: '', redirectTo: '/stocks', pathMatch: 'full' }

];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class RoutingModule { }
