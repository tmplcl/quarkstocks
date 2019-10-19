import {Component, OnInit} from '@angular/core';
import {DefaultService, Ping, Ticker} from '../api';


@Component({
  selector: 'app-ticker',
  templateUrl: './ticker.component.html',
  styleUrls: ['./ticker.component.css']
})
export class TickerComponent implements OnInit {
  ping: Ping;
  dataSource: Ticker[] = [];
  displayedColumns: string[] = ['id', 'symbol', 'issuer'];

  constructor(private defaultService: DefaultService) {
  }

  ngOnInit() {
    this.defaultService.apiPingGet().subscribe((ping: Ping) => {
      this.ping = ping;
      console.log(ping);
    });

    this.defaultService.apiTickerGet(0, 10).subscribe((tickers: Ticker[]) => {
      this.dataSource = tickers;
    });


  }


}
