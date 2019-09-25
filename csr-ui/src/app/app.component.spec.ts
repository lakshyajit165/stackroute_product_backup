import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
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
import {NavigationComponent} from './navigation/navigation.component'

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
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
      ],
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
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'csr-ui'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('csr-ui');
  });

  it('should render title in a h1 tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to csr-ui!');
  });
});
