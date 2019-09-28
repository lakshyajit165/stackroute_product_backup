import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {
  urlTaken: any;
  responseTaken: any;
  responseResolved: object;
  urlResolved: any;
  constructor(private _http: HttpClient) {
   }
   getdetailsTaken(): Observable<any> {
    return  this._http.get<any>('http://localhost:8080/tickets/csr/taken');
   }
   getdetailsResolved(): Observable<any> {
    return  this._http.get<any>('http://localhost:8080/tickets/csr/resolved');
   }
}
