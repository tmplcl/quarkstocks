import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TickerComponent} from '../ticker/ticker.component';


const routes: Routes = [
  { path: 'home', component: TickerComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }

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
