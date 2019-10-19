import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {TickerComponent} from './ticker/ticker.component';
import {ApiModule, BASE_PATH} from './api';
import {environment} from '../environments/environment';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatIconModule, MatListModule, MatSidenavModule, MatTableModule, MatToolbarModule} from '@angular/material';
import {RoutingModule} from './routing/routing.module';
import { HeaderComponent } from './navigation/header/header.component';
import { SidenavListComponent } from './navigation/sidenav-list/sidenav-list.component';


@NgModule({
  declarations: [
    AppComponent,
    TickerComponent,
    HeaderComponent,
    SidenavListComponent
  ],
  imports: [
    BrowserModule,
    ApiModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatSidenavModule,
    RoutingModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule
  ],
  providers: [{provide: BASE_PATH, useValue: environment.API_BASE_PATH}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
