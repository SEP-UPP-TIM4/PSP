import { Component, ViewChild } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { Observable, ReplaySubject } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { MatTable } from '@angular/material/table';

export interface PaymentMethod {
  id: number,
  name: string;
  url: string;
}

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent {
  displayedColumns: string[] = ['name', 'url'];
  dataToDisplay: PaymentMethod[] = [
    { id: 1, name: 'Pay-Pal', url: 'paypal-url' },
    { id: 2, name: 'QR code', url: 'qr-url' },
    { id: 3, name: 'Credit card', url: 'cc-url' },
  ];
  newName: string = '';
  newUrl: string = '';
  selectedTableRow!: any;
  @ViewChild(MatTable) table!: MatTable<PaymentMethod>;

  selectedRowIndex = -1;

  selectedRow(row: any) {
    this.selectedTableRow = row;
    this.selectedRowIndex = row.id;
  }

  addData() {
    this.dataToDisplay.push({ id: this.dataToDisplay[this.dataToDisplay.length-1].id + 1, name: this.newName, url: this.newUrl });
    this.table.renderRows();
    this.newName = '';
    this.newUrl = '';
  }

  removeData() {
    for (let i = 0; i < this.dataToDisplay.length; i++) {
      if (this.dataToDisplay[i].id === this.selectedTableRow.id) {
        this.dataToDisplay.splice(i, 1)
        this.table.renderRows();
        break;
      }
    }

  }
}
