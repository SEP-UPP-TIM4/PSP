import { Component, ViewChild } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { MatTable } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';

export interface PaymentMethod {
  paymentMethodId: number;
  paymentName: string;
  paymentRequestId: number;
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
  
  constructor(private authService: AuthenticationService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    var idString = this.route.snapshot.paramMap.get('id');
    if(idString != null){
      var id: number = +idString;
      this.getPaymentMethods(id);
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

  selectedRow(row: any) {
    this.selectedTableRow = row;
    this.selectedRowIndex = row.id;
  }

  pay(paymentMethodId: number, paymentRequestId: number){
    this.authService.processPayment(paymentMethodId, paymentRequestId).subscribe((data: any) => {
      alert("Redirektuj ga...");
    }, (err) => {
      alert("An error occurred, please try again...");
    })
  }
}
