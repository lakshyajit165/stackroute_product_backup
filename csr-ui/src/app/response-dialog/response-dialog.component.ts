import { Component, OnInit,Inject, Input} from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ResponseService} from '../response.service'
import { Router } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Response } from '../response-interface';

@Component({
 selector: 'app-response-dialog',
 templateUrl: './response-dialog.component.html',
 styleUrls: ['./response-dialog.component.css']
})
export class ResponseDialogComponent implements OnInit {

 msg: Object;
 usermail: string;
 response: string;
 result: Response;

 constructor(  private http: HttpClient,
   private router: Router,
   private response_service: ResponseService,
   @Inject(MAT_DIALOG_DATA) public data) {
   }

 ngOnInit() {
   this.response_service.getResponse().subscribe(res => {
     this.response=res.result;
     console.log("hello");
     console.log(this.response);
   })
 }

 replyViaMail(usermail:string){
   
  this.result = {
     response: this.response,
     usermail: usermail
   }
   console.log(this.result);
   console.log();
   //send response with mail and response
   this.response_service.sendUserMailforResponse(this.result);
 }
}
