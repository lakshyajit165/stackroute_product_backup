import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material';
import { CourseDialogComponentComponent } from './course-dialog-component/course-dialog-component.component';
import { MatDialogRef } from '@angular/material';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

// export interface DialogData {
//   animal: string;
// }
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  description: string;
  messages: string[];
  complaintUrl: string;

  private serverUrl = 'http://localhost:8080/socket';
  private stompClient;


  constructor(public dialog: MatDialog, public http: HttpClient) {
    this.initializeWebSocketConnection();

  }

  ngOnInit() {
    this.messages = [];
    this.complaintUrl = 'http://localhost:8080/tickets/complaint';
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CourseDialogComponentComponent, {
      width: '350px',
      data: {description: this.description}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');

      if (result !== undefined) {
        this.description = result.description;
        console.log(this.description);
        this.save(this.description);
      }
    });
  }

  displayAndSendMessage(message1: string) {
    console.log(message1);
    // this.messages.push(message1);
    this.stompClient.send('/app/send/message' , {}, message1);
  }

  public save(desc: string) {
    // return this.http.post<Observable>(this.complaintUrl, description);
    // console.log("save");

      this.http.post<any>(this.complaintUrl, desc).subscribe(result => {
        console.log(result);
      });
  }

  initializeWebSocketConnection() {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    // let that = this;
    this.stompClient.connect({}, (frame) => {
      this.stompClient.subscribe('/chat', (message) => {
        if (message.body) {
          // $(".chat").append("<div class='message'>"+message.body+"</div>")
          this.messages.push(message.body);
          console.log(message.body);
        }
      });
    });
  }
}
