import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TicketServiceService } from '../ticket-service.service';
import { Observable } from 'rxjs';
import { Ticket } from '../ticket-interface';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-open-ticket-list',
  templateUrl: './open-ticket-list.component.html',
  styleUrls: ['./open-ticket-list.component.css']
})
export class OpenTicketListComponent implements OnInit {

  tickets: any;

  constructor(
    private http: HttpClient,
    private ticketService: TicketServiceService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.ticketService.getProduct().subscribe(res => {
      this.tickets = res.result;
      console.log(this.tickets);
    });
  }


  assignMe(ticket: Ticket) {
    console.log(ticket);
    this.router.navigate(['/ticketassigned'], { state: { res: ticket }});
    // this.router.navigateByUrl('/ticket-assigned');
  }


  ngOnInit() {
    

  }

  }
