import { Injectable } from '@angular/core';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { catchError } from 'rxjs/operators';

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


}
