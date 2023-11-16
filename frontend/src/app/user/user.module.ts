import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import {CommonsModule} from "../commons/commons-module";
import {TransactionsComponent} from "./pages/transactions/transactions.component";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "../auth/auth.interceptor";


@NgModule({
  declarations: [
    TransactionsComponent
  ],
  imports: [
    CommonModule,
    CommonsModule,
    UserRoutingModule
  ],
})
export class UserModule { }
