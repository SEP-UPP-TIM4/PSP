import { Component } from '@angular/core';
import { FormControl, Validators, NgForm } from '@angular/forms';
import { AuthenticationService } from '../service/authentication.service';
import { StorageService } from '../service/storage.service';
import { Router } from '@angular/router';
import { LoginDTO } from '../dto/LoginDTO';
@Component({
  selector: 'app-sign-in-page',
  templateUrl: './sign-in-page.component.html',
  styleUrls: ['./sign-in-page.component.css']
})
export class SignInPageComponent {

  email = new FormControl('', [Validators.required]);
  hide = true;

  constructor(private authService: AuthenticationService, private storageService: StorageService, private router: Router) { }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  loginUser(credentials: NgForm) {
    let loginDTO: LoginDTO = { username: credentials.value.username, password: credentials.value.password };
    this.authService.login(loginDTO).subscribe((data: any) => {
      this.storageService.storeTokenData(data.jwtToken);
      if (this.storageService.getRoleFromToken() == 'ROLE_ADMIN') {
        this.router.navigateByUrl('/admin')
      }else{
        this.router.navigateByUrl('/merchant')
      }
    }, (err) => {
      alert("Not valid credentials");
    })
  }
}
