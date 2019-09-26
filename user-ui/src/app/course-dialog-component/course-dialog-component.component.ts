import { Component, OnInit , Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';


export interface DialogData {
  description: string;
}

@Component({
  selector: 'app-course-dialog-component',
  templateUrl: './course-dialog-component.component.html',
  styleUrls: ['./course-dialog-component.component.css']
})
export class CourseDialogComponentComponent implements OnInit {

  private complaintUrl: string;
  constructor(
    public dialogRef: MatDialogRef<CourseDialogComponentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit() {
  }

  dialogClose(){
    this.dialogRef.close();
  }


}
