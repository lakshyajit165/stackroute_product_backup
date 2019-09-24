import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { OpenTicketListComponent } from './open-ticket-list/open-ticket-list.component';
import { PerformanceComponent } from './performance/performance.component';
import {MatListModule} from '@angular/material/list';
import { TicketDetailComponent } from './ticket-detail/ticket-detail.component';
import { ChatComponent } from './chat/chat.component';
import { AssignedTicketComponent } from './assigned-ticket/assigned-ticket.component';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import { ResponseDialogComponent } from './response-dialog/response-dialog.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSelectModule} from '@angular/material/select';
import { ReportUserComponent } from './report-user/report-user.component';

import {
  MatFormFieldModule,
  MatRippleModule
} from '@angular/material';
import { NavigationComponent } from './navigation/navigation.component'
@NgModule({
  declarations: [
    AppComponent,
    OpenTicketListComponent,
    PerformanceComponent,
    TicketDetailComponent,
    ChatComponent,
    AssignedTicketComponent,
    ResponseDialogComponent,
    ReportUserComponent,
    NavigationComponent,
  
  ],

  entryComponents: [ResponseDialogComponent,ReportUserComponent],
  
  imports: [
    MatDialogModule,
    MatInputModule,
    MatGridListModule,
    MatButtonModule,
    MatListModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatSelectModule,
    MatFormFieldModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
