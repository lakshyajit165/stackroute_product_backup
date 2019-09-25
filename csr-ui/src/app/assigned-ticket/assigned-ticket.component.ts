import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
@Component({
  selector: 'app-assigned-ticket',
  templateUrl: './assigned-ticket.component.html',
  styleUrls: ['./assigned-ticket.component.css']
})
export class AssignedTicketComponent implements OnInit {

  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe( params => console.log(params) );
}
  ngOnInit() {
  }

}
