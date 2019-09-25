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
  // postData(data)
  // {
  //   return this.http.post<Iinventory[]>("http://localhost:3000/data1",data);           
  //        //array type for observables
  // }
  

  sendUserMail(mail: string)
  {
    let config = {
      headers: {'Content-Type': Text},
      transformRequest: [],
      //IMPORTANT
     
  }
    return this.http.post<any>("http://localhost:8182/sendEmail",mail).subscribe(res => {
      console.log(res);
    });    
  }
  
}
