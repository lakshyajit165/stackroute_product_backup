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
    return this.http.get<any>("http://localhost:8182/tickets/open");
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
    return this.http.post<any>("http://localhost:8182/callbackmail",mail).subscribe(res => {
      console.log(res);
    });    
  }


  //for now json server for getting commmand details
  //using mock data for now.....we will integrate it with command repo service
  getCommandName():Observable<any>{
    return this.http.get<any>("http://localhost:3000/result");
  }

  
  setStatusforMail(ticket: Object)
  {
    return this.http.patch<any>("http://localhost:8182/tickets/status/callbackmail",ticket).subscribe(res => {
      console.log(res);
    });   
  }


  setStatusforResolve(ticket: Object)
  {
    return this.http.patch<any>("http://localhost:8182/tickets/status/resolved",ticket).subscribe(res => {
      console.log(res);
    });   
  }

  }



