import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../ajout-media/ajout-media.component";

@Component({
  selector: 'app-dialog-etiquettes',
  templateUrl: './dialog-etiquettes.component.html',
  styleUrls: ['./dialog-etiquettes.component.css']
})
export class DialogEtiquettesComponent {
  constructor(public dialogRef: MatDialogRef<DialogEtiquettesComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  onNoClick(): void {
    this.data.ajoutEtiquette = "";
    this.dialogRef.close();
  }
}
