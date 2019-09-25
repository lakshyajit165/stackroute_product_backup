import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { OpenTicketListComponent } from './open-ticket-list/open-ticket-list.component';
import { PerformanceComponent } from './performance/performance.component';
import {MatListModule} from '@angular/material/list';
import { TicketDetailComponent } from './ticket-detail/ticket-detail.component';
import { ChatComponent } from './chat/chat.component';
import { AssignedTicketComponent } from './assigned-ticket/assigned-ticket.component';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatDialogModule} from '@angular/material/dialog';
import { ResponseDialogComponent } from './response-dialog/response-dialog.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSelectModule} from '@angular/material/select';
import { ReportUserComponent } from './report-user/report-user.component';
import { MatIconModule, MatInputModule, MatFormFieldModule } from '@angular/material';
import {
  MatRippleModule
} from '@angular/material';
import { NavigationComponent } from './navigation/navigation.component'
import { HttpClientModule } from '@angular/common/http';

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
    HttpClientModule,
    MatSelectModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
