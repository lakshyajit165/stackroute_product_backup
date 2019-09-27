import { Component, OnInit, Output } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatDialogConfig} from "@angular/material";
import {ResponseDialogComponent} from "../response-dialog/response-dialog.component"
import {MatSnackBar} from '@angular/material/snack-bar';
import {ReportUserComponent} from "../report-user/report-user.component";
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TicketServiceService } from '../ticket-service.service';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  durationInSeconds = 5;

  id: string;
  msg: Object;    //send this in dialog
  usermail: string;      // //send this in dialog
   
  commands: any;
  commandName: string[]= [];


  // ngOnInit() {
  //   this.route.paramMap.pipe(map(paramMap => {
  //     this.id = paramMap.get('id');
  //     console.log("id" + this.id);
      
  //   }))
  // }
// ngOnInit()
// {

// }

ngOnInit() {
  this.ticketService.setStatusforEngage(this.msg);
  console.log("this is current state1");
 this.ticketService.getCommandName().subscribe(res =>{
    this.commands=res;
 //   console.log(res);
    this.searchCommands();
    });
   
  
  
}

  constructor(
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private dialog1: MatDialog,
    private route: ActivatedRoute,
    private router: Router,
    private ticketService: TicketServiceService,
    private http: HttpClient) {
   
    if(this.router.getCurrentNavigation().extras.state !== undefined){
      this.msg = this.router.getCurrentNavigation().extras.state.res;
      this.usermail = this.router.getCurrentNavigation().extras.state.res.usermail;
      console.log(this.msg);
    }
   
  }

  openSnackBar(message, action) {

    this.snackBar.open(message, action, {duration: 2000});
    this.ticketService.setStatusforResolve(this.msg);
    // this.router.navigate(['/home']);

  }
  
  openDialogforReport()
  {
   this.dialog.open(ReportUserComponent);
  }

  openDialogforResponse():void
 {
   console.log(this.usermail);
  // this.dialog1.open(ResponseDialogComponent);
  const dialogRef = this.dialog1.open(ResponseDialogComponent,
   {
     data: { mail: this.usermail, msg: this.msg}
   });
 }

  sendMail()
  {
  // this.ticketService.sendUserMail();
   // this.ticketService.sendUserMail("lakshyajit165@gmail.com");
   console.log("this is current state2");
   this.ticketService.sendUserMail(this.usermail);
   console.log("this is current state3");
   console.log(this.msg);
   this.ticketService.setStatusforMail(this.msg);
  }


  myControl = new FormControl();
  filteredOptions: Observable<string[]>;
  

searchCommands(){

this.commands.forEach(element => {
  if(element.status=="active")
  {
    console.log(element.name);
    this.commandName.push(element.name);
  }
});

//console.log(this.commandName);
//for filtering the commands according to the command name
this.filteredOptions = this.myControl.valueChanges
.pipe(
  startWith(''),
  map(value => this._filter(value))
);
}


private _filter(value: string): string[] {
  const filterValue = value.toLowerCase();

  return this.commandName.filter(option => option.toLowerCase().includes(filterValue));
}

}

