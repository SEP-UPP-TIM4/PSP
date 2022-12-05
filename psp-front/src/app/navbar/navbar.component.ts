import { Component, OnInit } from '@angular/core';
import { StorageService } from '../service/storage.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit  {
  constructor(private storageService: StorageService, private router: Router) { }

  logout() {
    this.storageService.clearToken()
    this.router.navigate([''])
  }

  ngOnInit(): void {
    
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    
  }


  getRole() {
    return this.storageService.getRoleFromToken()
  }

  isLoggedIn() {
    return this.storageService.getRoleFromToken() !== ""
  }

  isLoggedInAdmin() {
    return this.storageService.getRoleFromToken() === "ROLE_ADMIN"
  }
}
