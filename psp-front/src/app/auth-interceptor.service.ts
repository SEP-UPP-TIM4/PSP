import { Injectable } from '@angular/core';
import { StorageService } from './service/storage.service';
import { PlatformLocation } from '@angular/common';
import { AuthenticationService } from './service/authentication.service';
import { HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService {

  constructor(private storageService: StorageService, private platformLocation: PlatformLocation, private authenticationService: AuthenticationService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.storageService.getToken()
    if (token) {
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }
    return next.handle(req)
  }
}
