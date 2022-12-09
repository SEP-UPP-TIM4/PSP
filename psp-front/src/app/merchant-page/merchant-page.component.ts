import { Component, ViewChild } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { Observable, ReplaySubject } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { MatTable } from '@angular/material/table';
import { AuthenticationService } from '../service/authentication.service';
import { PaymentMethodDTO } from '../dto/PaymentMethodDTO';
import { MerchantPaymentMethodDTO } from '../dto/MerchantPaymentMethodDTO';

export interface PaymentMethod {
  id: number;
  bankPayment: boolean;
  name: string;
  bankName: string;
  username: string;
}

@Component({
  selector: 'app-merchant-page',
  templateUrl: './merchant-page.component.html',
  styleUrls: ['./merchant-page.component.css']
})
export class MerchantPageComponent {
  displayedColumns: string[] = ['name', 'username', 'bank'];
  dataToDisplay: PaymentMethod[] = [];
  allPaymentMethods: PaymentMethod[] = [];
  banks: any = [];
  methodSelected: any;
  bankSelected: any;
  newUsername: string = '';
  newPassword: string = '';
  selectedTableRow!: any;
  bankPayment: boolean = false;
  @ViewChild(MatTable) table!: MatTable<PaymentMethod>;

  selectedRowIndex = -1;

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.getMerchantPaymentMethods();
    this.getPaymentMethods();
    this.getBanks();
  }

  getBanks() {
    this.authService.getBanks().subscribe((data: any) => {
      this.banks = data;
    })
  }

  selectionChanged() {
    for (let i = 0; i < this.allPaymentMethods.length; i++) {
      if (this.allPaymentMethods[i].id === this.methodSelected) {
        this.bankPayment = this.allPaymentMethods[i].bankPayment;
        if(!this.bankPayment){
          this.bankSelected = '';
        }
      }
    }
  }

  getPaymentMethods() {
    this.authService.getPaymentMethods().subscribe((data: any) => {
      this.allPaymentMethods = data;
      this.table.renderRows();
    })
  }

  getMerchantPaymentMethods() {
    this.authService.getMerchantPaymentMethods().subscribe((data: any) => {
      this.dataToDisplay = data;
      this.table.renderRows();
    })
  }

  check(event: any) {
    this.bankPayment = event.checked;
  }

  selectedRow(row: any) {
    this.selectedTableRow = row;
    this.selectedRowIndex = row.id;
  }

  addData() {
    let merchantPaymentMethodDTO: MerchantPaymentMethodDTO = { username: this.newUsername, password: this.newPassword, paymentMethodId: this.methodSelected, bankId: this.bankSelected };
    this.authService.addPaymentMethodToMerchant(merchantPaymentMethodDTO).subscribe((data: any) => {
      this.getMerchantPaymentMethods();
      this.newUsername = '';
      this.newPassword = '';
    }, (err) => {
      alert("An error occurred, please try again...");
    })
  }

  removeData() {
    for (let i = 0; i < this.dataToDisplay.length; i++) {
      if (this.dataToDisplay[i].id === this.selectedTableRow.id) {
        this.authService.deleteMerchantCredentials(this.dataToDisplay[i].id).subscribe((data: any) => {
          this.getMerchantPaymentMethods();
        })
        break;
      }
    }

  }
}
