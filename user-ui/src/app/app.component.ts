import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material";
import { CourseDialogComponentComponent } from './course-dialog-component/course-dialog-component.component';
import { MatDialogRef } from '@angular/material';


export interface DialogData {
  animal: string;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  description: string;
  messages: string[];

    constructor(public dialog: MatDialog){
      this.messages = [];
    }
  
  openDialog(): void {
    const dialogRef = this.dialog.open(CourseDialogComponentComponent, {
      width: '350px',
      data: {description: this.description}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.description = result.description;
      console.log(this.description);
      });
  }

  DisplayMessage(message1: string) {
    console.log(message1);
    this.messages.push(message1);
  }
  
    ngOnInit() {
    }
}
