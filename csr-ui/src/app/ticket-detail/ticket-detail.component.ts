import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatDialogConfig} from "@angular/material";
import {ResponseDialogComponent} from "../response-dialog/response-dialog.component"
import {MatSnackBar} from '@angular/material/snack-bar';
import {ReportUserComponent} from "../report-user/report-user.component";
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TicketServiceService } from '../ticket-service.service';
@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  durationInSeconds = 5;

  id: string;
  msg: Object;
  usermail: string;
    
  // ngOnInit() {
  //   this.route.paramMap.pipe(map(paramMap => {
  //     this.id = paramMap.get('id');
  //     console.log("id" + this.id);
      
  //   }))
  // }
// ngOnInit()
// {

// }

ngOnInit() {
  
}

  constructor(private _snackBar: MatSnackBar,private dialog:MatDialog,private dialog1:MatDialog, private route: ActivatedRoute,private router: Router
    ,private _fetch: TicketServiceService,private http: HttpClient) { 
    // if(this.router.getCurrentNavigation().extras.state !== undefined){
    //   this.msg = this.router.getCurrentNavigation().extras.state.res.message;
    //   console.log(this.msg);
    // }
    if(this.router.getCurrentNavigation().extras.state !== undefined){
      this.msg = this.router.getCurrentNavigation().extras.state.res;
      this.usermail = this.router.getCurrentNavigation().extras.state.res.usermail;
      console.log(this.msg);
    }
   
  }

  openSnackBar(message, action) {

    this._snackBar.open(message, action, {duration: 2000});
  //  this._fetch.setStatus("callBackMailInitiated");

  }
  
  openDialogforReport()
  {
   this.dialog.open(ReportUserComponent);
  }

  openDialogforResponse()
  {
    this.dialog1.open(ResponseDialogComponent);
  }

  sendMail()
  {
  // this._fetch.sendUserMail();
   // this._fetch.sendUserMail("lakshyajit165@gmail.com");
   this._fetch.sendUserMail(this.usermail);
  }

}
