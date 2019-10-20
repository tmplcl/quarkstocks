import {Component, OnInit} from '@angular/core';
import {DefaultService, Stock, Ticker} from "../api";
import {FormBuilder, FormControl} from '@angular/forms';
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  dataSource: Stock[];
  displayedColumns: string[] = ['date', 'close', 'symbol'];
  stockForm: any;
  ticker: Ticker[];
  autoCompleteSymbols: string[];
  dateFrom : Date;
  dateTo : Date;

  constructor(private formBuilder: FormBuilder, private defaultService: DefaultService, private datePipe: DatePipe) {
    this.stockForm = this.formBuilder.group({ticker: ''});
  }

  ngOnInit() {
    this.defaultService.apiTickerGet(0, 100).subscribe(value => {
      this.autoCompleteSymbols = value.map(value1 => value1.symbol);
    })
  }

  onSubmit(value: any) {
    console.log(value.toDate)

    this.defaultService.apiTickerSymbolStocksGet(
      value.ticker,
      this.datePipe.transform(this.dateFrom, "yyyy-MM-dd"),
      this.datePipe.transform(this.dateTo, "yyyy-MM-dd")).subscribe(value1 => {
        this.dataSource = value1;
        console.log(value1);
      })
  }
}
