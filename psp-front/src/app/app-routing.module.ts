import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { GettingStartedPageComponent } from './getting-started-page/getting-started-page.component';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { MerchantPageComponent } from './merchant-page/merchant-page.component';

const routes: Routes = [{path: '', component: HomePageComponent},
                        {path: 'sign-in', component: SignInPageComponent},
                        {path: 'getting-started', component: GettingStartedPageComponent},
                        {path: 'admin', component: AdminPageComponent},
                        {path: 'merchant', component: MerchantPageComponent}
                      ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
