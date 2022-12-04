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
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomePageComponent,
    SignInPageComponent,
    GettingStartedPageComponent
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
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
