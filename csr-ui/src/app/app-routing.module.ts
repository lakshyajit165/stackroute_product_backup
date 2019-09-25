import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OpenTicketListComponent } from "../app/open-ticket-list/open-ticket-list.component";
import { PerformanceComponent } from "../app/performance/performance.component";
import {AssignedTicketComponent} from "../app/assigned-ticket/assigned-ticket.component"

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: OpenTicketListComponent },
  { path: 'performance', component: PerformanceComponent },
  { path: 'ticket-assigned', component: AssignedTicketComponent },
  // { path: 'ticket-assigned/:id', component: AssignedTicketComponent },
  // { path: 'home/ticket-assigned/:id', component: AssignedTicketComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
