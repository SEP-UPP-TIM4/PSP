import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { GettingStartedPageComponent } from './getting-started-page/getting-started-page.component';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';

const routes: Routes = [{path: '', component: HomePageComponent},
                        {path: 'sign-in', component: SignInPageComponent},
                        {path: 'getting-started', component: GettingStartedPageComponent},
                      ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
