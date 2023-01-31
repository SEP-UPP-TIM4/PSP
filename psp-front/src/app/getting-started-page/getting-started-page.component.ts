import { Component } from '@angular/core';
import { FormControl, Validators, NgForm } from '@angular/forms';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { RegistrationDTO } from '../dto/RegistrationDTO';

@Component({
  selector: 'app-getting-started-page',
  templateUrl: './getting-started-page.component.html',
  styleUrls: ['./getting-started-page.component.css']
})
export class GettingStartedPageComponent {

  constructor(private authService: AuthenticationService, private router: Router) { }

  registerUser(credentials: NgForm) {
    let registrationDTO: RegistrationDTO = { name: credentials.value.name, username: credentials.value.username, password: credentials.value.password };
    this.authService.register(registrationDTO).subscribe((data: any) => {
        this.router.navigateByUrl('/sign-in')
        alert(data.apiKey);
        alert(data.secret);
        
    }, (err) => {
      alert("An error occurred, please try again...");
    })
  }
}
