import { config } from "src/shared"
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDTO } from "../dto/LoginDTO";
import { PaymentMethodDTO } from "../dto/PaymentMethodDTO"
import { RegistrationDTO } from "../dto/RegistrationDTO";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  private authServiceUrl = "AUTH-SERVICE/api/v1"
  private loginUrl = "/users/login"
  private registrationUrl = "/merchant"
  private paymentMethodUrl = "/payment-method"
  private bankUrl = "/bank"
  
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

  getBanks() {
    return this.http.get(`${config.baseUrl}${this.authServiceUrl}${this.bankUrl}`)
  }

  addPaymentMethod(paymentMethodDTO: PaymentMethodDTO) {
    return this.http.post(`${config.baseUrl}${this.authServiceUrl}${this.paymentMethodUrl}`, paymentMethodDTO);
  }

  deletePaymentMethod(paymentMethodId: number) {
    return this.http.delete(`${config.baseUrl}${this.authServiceUrl}${this.paymentMethodUrl}/${paymentMethodId}`);
  }
}
