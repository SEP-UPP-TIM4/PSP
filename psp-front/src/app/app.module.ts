import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import{MatIconModule} from '@angular/material/icon'
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { GettingStartedPageComponent } from './getting-started-page/getting-started-page.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { MatTableModule } from '@angular/material/table';
import { AuthInterceptorService } from './auth-interceptor.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MerchantPageComponent } from './merchant-page/merchant-page.component';
import {MatSelectModule} from '@angular/material/select';
import { ProcessPaymentPageComponent } from './process-payment-page/process-payment-page.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomePageComponent,
    SignInPageComponent,
    GettingStartedPageComponent,
    AdminPageComponent,
    MerchantPageComponent,
    ProcessPaymentPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatToolbarModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    HttpClientModule,
    MatTableModule,
    MatCheckboxModule,
    MatSelectModule
  ],
  providers: [AppComponent,
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptorService,
        multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
