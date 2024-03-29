import { Component, ViewChild } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { MatTable } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';

export interface PaymentMethod {
  paymentMethodId: number;
  paymentName: string;
  paymentRequestId: number;
  paymentMethodUrl: string;
}

@Component({
  selector: 'app-process-payment-page',
  templateUrl: './process-payment-page.component.html',
  styleUrls: ['./process-payment-page.component.css']
})
export class ProcessPaymentPageComponent {
  displayedColumns: string[] = ['paymentName'];
  dataToDisplay: PaymentMethod[] = [];
  selectedTableRow!: any;
  @ViewChild(MatTable) table!: MatTable<PaymentMethod>;

  selectedRowIndex = -1;

  apiKey: string = '';
  amount: number = 0;
  successUrl: string = '';
  failedUrl: string = '';
  errorUrl: string = '';
    
  constructor(private authService: AuthenticationService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    var idString = this.route.snapshot.paramMap.get('id');
    if(idString != null){
      var id: number = +idString;
      this.getPaymentMethods(id);
      this.getPaymentRequestData(id);
    }else{
      alert("An error occured...")
    }
  }

  getPaymentMethods(id: number) {
    this.authService.getPaymentMethodsForClient(id).subscribe((data: any) => {
      this.dataToDisplay = data;
      this.table.renderRows();
    })
  }

  getPaymentRequestData(id: number) {
    this.authService.getPaymentrequestData(id).subscribe((data: any) => {
      this.apiKey = data.apiKey;
      this.amount = data.amount;
      this.successUrl = data.successUrl;
      this.failedUrl = data.failedUrl;
      this.errorUrl = data.errorUrl;
    })
  }

  selectedRow(row: any) {
    this.selectedTableRow = row;
    this.selectedRowIndex = row.id;
  }

  pay(paymentMethodId: number, paymentMethodUrl: string){
    let body = {
      amount: this.amount,
      successUrl: this.successUrl,
      failedUrl: this.failedUrl,
      errorUrl: this.errorUrl
    }
    this.authService.processPayment(paymentMethodUrl, body, this.apiKey, paymentMethodId).subscribe((data: any) => {
      window.location.href = data.url;
    }, (err) => {
      alert("An error occurred, please try again...");
    })
  }
}
