import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material';
import { CourseDialogComponentComponent } from './course-dialog-component/course-dialog-component.component';
import { MatDialogRef } from '@angular/material';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface DialogData {
  animal: string;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  description: string;
  messages: string[];
  complaintUrl: string;

  constructor(public dialog: MatDialog, public http: HttpClient) {
    this.messages = [];
  }

  ngOnInit() {
    this.complaintUrl = 'http://localhost:8080/tickets/complaint';
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CourseDialogComponentComponent, {
      width: '350px',
      data: {description: this.description}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.description = result.description;

      if (this.description !== '') {
        console.log(this.description);
        this.save(this.description);
      }
    });
  }

  DisplayMessage(message1: string) {
    console.log(message1);
    this.messages.push(message1);
  }

  public save(desc: string) {
    // return this.http.post<Observable>(this.complaintUrl, description);
    // console.log("save");
      this.http.post<any>(this.complaintUrl, desc).subscribe(result => {
        console.log(result);
      });
  }
}
