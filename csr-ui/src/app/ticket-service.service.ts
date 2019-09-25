import { Injectable } from '@angular/core';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Ticket } from './ticket-interface';
import { Observable } from 'rxjs';

import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class TicketServiceService {

  constructor(private http: HttpClient) { }

  getProduct():Observable<any>
  {
    return this.http.get<any>("http://localhost:8182/tickets");
  }
  
}
