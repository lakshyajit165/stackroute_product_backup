import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  private messages: string[];
  private serverUrl = 'http://localhost:8080/socket';
  private stompClient;


  constructor() {
    this.initializeWebSocketConnection();
   }

  ngOnInit() {
    this.messages = [];
  }

  displayAndSendMessage(message1: string) {
    console.log(message1);
    // this.messages.push(message1);
    this.stompClient.send('/app/send/message' , {}, message1);
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
