import { config } from "src/shared"
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDTO } from "../dto/LoginDTO";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  private loginUrl = "/users/login"
  
  constructor(private http: HttpClient) { }

  login(loginDTO: LoginDTO) {
    return this.http.post(`${config.baseUrl}${this.loginUrl}`, loginDTO)
  }
}
