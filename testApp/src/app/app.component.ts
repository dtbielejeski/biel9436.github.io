import { Component } from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog'
import {DialogComponent} from "./dialog/dialog.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'testApp';

  constructor(private dialog: MatDialog) {}
  openDialog() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.ariaDescribedBy = "exhibit1";
    dialogConfig.ariaLabelledBy = "ui-iid-2";
    dialogConfig.height = "400px";
    dialogConfig.width = "600px";
    this.dialog.open(DialogComponent, dialogConfig);
  }

}
