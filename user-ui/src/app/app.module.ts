import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material';
import {MatToolbarModule} from '@angular/material';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SpeedDialFabComponent } from './speed-dial-fab/speed-dial-fab.component';
import { ChatWindowComponent } from './chat-window/chat-window.component';
import { MatIconModule, MatInputModule, MatFormFieldModule } from '@angular/material';
import {MatDialogModule} from '@angular/material';
import { CourseDialogComponentComponent } from './course-dialog-component/course-dialog-component.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';




@NgModule({
  declarations: [
    AppComponent,
    SpeedDialFabComponent,
    ChatWindowComponent,
    CourseDialogComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [CourseDialogComponentComponent],
  exports: [CourseDialogComponentComponent],
  bootstrap: [AppComponent],
  entryComponents: [CourseDialogComponentComponent]
})
export class AppModule { }
