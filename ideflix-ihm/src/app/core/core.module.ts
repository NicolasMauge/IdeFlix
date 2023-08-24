import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./interceptors/token.interceptor";
import {ErrorInterceptor} from "./interceptors/error.interceptor";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {RouterModule} from "@angular/router";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {ActionbarComponent} from './components/actionbar/actionbar.component';


@NgModule({
  declarations: [
    NavbarComponent,
    ActionbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    MatSnackBarModule
  ],
  exports: [
    NavbarComponent,
    ActionbarComponent
  ],

  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ]
})
export class CoreModule {
}
