import { Component, OnInit } from '@angular/core';
import { PerformanceService } from '../performance.service';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.css']
})
export class PerformanceComponent implements OnInit {

 constructor(private performance: PerformanceService) {}
 urlTaken: any;
 responseTaken: any;
 responseResolved: any;
 urlResolved: any;
 dates = [];
 timestamp = [];
 queryTakenCount: any = [];
 queryResolvedCount: any = [];
 filledArray: any = [];
 public barChartOptions: ChartOptions = {
   responsive: true,
   scales: { xAxes: [{}], yAxes: [{}] },
   plugins: {
     datalabels: {
       anchor: 'end',
       align: 'end',
     }
   }
 };
 public barChartLabels: Label[];
 public barChartType: ChartType;
 public barChartLegend = true;
 public barChartPlugins = [pluginDataLabels];
 public barChartData: ChartDataSets[];
 ngOnInit() {
   this.performance.getdetailsTaken().subscribe(res => {
   // console.log(res);
   this.urlTaken = res;
   this.responseTaken = this.urlTaken.result.results;
   // console.log(this.responseTaken);
   this.responseTaken.forEach(element => {
       // console.log('element is ', element);
       this.dates.push(element.timestamp);
       // console.log(this.dates);
       this.queryTakenCount.push(element.total);
       // console.log(this.queryTaken);
     });
 });
   this.performance.getdetailsResolved().subscribe(res => {
     this.urlResolved = res;
     this.responseResolved = this.urlResolved.result.results;
     this.responseResolved.forEach(element => {
       console.log(this.dates);
       this.queryResolvedCount.push(element.total);
       console.log(this.queryResolvedCount);
     });
 });
   console.log(this.queryTakenCount);
   this.barChartLabels = this.dates;
   this.barChartType = 'bar';
   this.barChartData = [
     {data: this.queryTakenCount , label: 'Queries Taken'},
     {data : this.queryResolvedCount, label : 'Queries Resolved'}
   ];
 }

}
