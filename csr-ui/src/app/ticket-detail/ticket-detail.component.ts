import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatDialogConfig} from "@angular/material";
import {ResponseDialogComponent} from "../response-dialog/response-dialog.component"
import {MatSnackBar} from '@angular/material/snack-bar';
import {ReportUserComponent} from "../report-user/report-user.component";
@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  durationInSeconds = 5;

    
  ngOnInit() {
  }


  constructor(private _snackBar: MatSnackBar,private dialog:MatDialog,private dialog1:MatDialog) { }

  openSnackBar(message, action) {
    this._snackBar.open(message, action, {duration: 2000});
  }
  
  openDialogforReport()
  {
   this.dialog.open(ReportUserComponent);
  }

  openDialogforResponse()
  {
    this.dialog1.open(ResponseDialogComponent);
  }

}
