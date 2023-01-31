import { Component } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { ActivatedRoute } from '@angular/router'; 

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.css']
})
export class AccountActivationComponent {

  activationSucceeded: any = null;

  constructor(private authService: AuthenticationService, private route: ActivatedRoute) { }
  
  ngOnInit(): void {
    let token = decodeURI(this.route.snapshot.paramMap.get('token') || "")
    this.authService.activateAccount(token).subscribe(
      (_data: any) => {
        this.activationSucceeded = true;
      }, (_err: Error) => {
        this.activationSucceeded = false;
      });
  }


}
