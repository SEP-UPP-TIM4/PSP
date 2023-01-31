import { config } from "src/shared"
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDTO } from "../dto/LoginDTO";
import { PaymentMethodDTO } from "../dto/PaymentMethodDTO"
import { RegistrationDTO } from "../dto/RegistrationDTO";
import { MerchantPaymentMethodDTO } from "../dto/MerchantPaymentMethodDTO";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  private authServiceUrl = "AUTH-SERVICE/api/v1"
  private loginUrl = "/users/login"
  private registrationUrl = "/merchant"
  private paymentMethodUrl = "/payment-method"
  private bankUrl = "/bank"
  private credentialsUrl = "/credentials"
  private paymentRequestUrl = "/payment-request"
  private processPaymentUrl = "/process-payment"
  private acctivateAccountUrl = "/users/confirm"
  
  constructor(private http: HttpClient) { }

  login(loginDTO: LoginDTO) {
    return this.http.post(`${config.baseUrl}${this.authServiceUrl}${this.loginUrl}`, loginDTO)
  }

  register(registrationDTO: RegistrationDTO) {
    return this.http.post(`${config.baseUrl}${this.authServiceUrl}${this.registrationUrl}`, registrationDTO)
  }

  getPaymentMethods() {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.paymentMethodUrl}`)
  }

  getPaymentMethodsForClient(id: number) {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.credentialsUrl}${this.paymentRequestUrl}/${id}`)
  }

  activateAccount(token: string) {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.acctivateAccountUrl}/${token}`)
  }

  getMerchantPaymentMethods() {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.credentialsUrl}`)
  }

  getBanks() {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.bankUrl}`)
  }

  addPaymentMethod(paymentMethodDTO: PaymentMethodDTO) {
    return this.http.post(`${config.baseUrl}${this.authServiceUrl}${this.paymentMethodUrl}`, paymentMethodDTO);
  }

  addPaymentMethodToMerchant(merchantPaymentMethodDTO: MerchantPaymentMethodDTO) {
    return this.http.post(`${config.baseUrl}${this.authServiceUrl}${this.credentialsUrl}`, merchantPaymentMethodDTO);
  }

  deletePaymentMethod(paymentMethodId: number) {
    return this.http.delete(`${config.baseUrl}${this.authServiceUrl}${this.paymentMethodUrl}/${paymentMethodId}`);
  }

  deleteMerchantCredentials(credentialsId: number) {
    return this.http.delete(`${config.baseUrl}${this.authServiceUrl}${this.credentialsUrl}/${credentialsId}`);
  }

  processPayment(paymentUrl: string, body: any, apiKey: string, paymentMethodId: number) {
    const headers= new HttpHeaders()
    .set('Authorization', apiKey);
    return this.http.post(`${config.baseUrl}${paymentUrl}?paymentMethodId=${paymentMethodId}`, body, {headers});
  }

  getPaymentrequestData(paymentRequestId: number) {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.paymentRequestUrl}/${paymentRequestId}`)
  }
}
