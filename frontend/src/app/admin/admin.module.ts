import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { ListUserComponent } from './pages/list-user/list-user.component';
import { AddUserComponent } from './pages/add-user/add-user.component';
import { EditUserComponent } from './pages/edit-user/edit-user.component';
import {CommonsModule} from "../commons/commons-module";
import { AddAccountComponent } from './pages/account/add-account/add-account.component';
import { EditAccountComponent } from './pages/account/edit-account/edit-account.component';
import { ListAccountComponent } from './pages/account/list-account/list-account.component';
import { ExcahngeRateComponent } from './pages/excahnge-rate/excahnge-rate.component';
import { OperationsComponent } from './pages/operations/operations.component';
import { MovementsComponent } from './pages/reports/movements/movements.component';
import { FrozenComponent } from './pages/reports/frozen/frozen.component';
import { DetailComponent } from './pages/reports/detail/detail.component';
import { TotalComponent } from './pages/reports/total/total.component';


@NgModule({
  declarations: [
    ListUserComponent,
    AddUserComponent,
    EditUserComponent,
    AddAccountComponent,
    EditAccountComponent,
    ListAccountComponent,
    ExcahngeRateComponent,
    OperationsComponent,
    MovementsComponent,
    FrozenComponent,
    DetailComponent,
    TotalComponent
  ],
  imports: [
    CommonModule,
    CommonsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
