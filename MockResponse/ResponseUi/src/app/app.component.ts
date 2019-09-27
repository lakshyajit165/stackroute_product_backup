import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PdfmakeService } from 'ng-pdf-make/pdfmake/pdfmake.service';
import { catchError } from 'rxjs/operators';
import {ResponseService} from './response.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(public pdfmake: PdfmakeService,
    private http: HttpClient,
    private router: Router,
    private response_service:ResponseService
  ) {
    
  } 

  title = 'ResponseUi';

  response:string;

   ngOnInit() {
    this.response_service.getResponse().subscribe(res => {
      this.response=res.result;
      console.log("hello");
      //  console.log(res);
      console.log(this.response);

      this.pdfmake.create();
 
    // Configure text styles  
    this.pdfmake.configureStyles({ header: { fontSize: 18, bold: true } });

    this.pdfmake.addText(this.response);
    })
     
   }   


   openPdf(){
    this.pdfmake.open();
}

printPdf(){
    this.pdfmake.print();
}

downloadPDF(){
    this.pdfmake.download();
}
     
     
 //now i have to convert this response that is in string format into pdf format    
  

}


