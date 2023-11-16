import { NgModule } from '@angular/core';

import { AuthRoutingModule } from './auth-routing.module';
import {LoginComponent} from "./pages/login/login.component";
import {CommonsModule} from "../commons/commons-module";


@NgModule({
  declarations: [ LoginComponent ],
  imports: [
    CommonsModule,
    AuthRoutingModule
  ],
    exports: [ LoginComponent ]
})
export class AuthModule { }
