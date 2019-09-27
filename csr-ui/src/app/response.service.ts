import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Response } from "./response-interface";
import { Router } from '@angular/router';
@Injectable({
 providedIn: 'root'
})
export class ResponseService {
 constructor(private http: HttpClient,) { }
 getResponse():Observable<any>
 {
   return this.http.get<any>("http://localhost:9000/response");
 }
 sendUserMailforResponse(response:Response)
 {
   return this.http.post<any>("http://localhost:8080/tickets/status/response",response).subscribe(res => {
     console.log(res);
   });
 }
}