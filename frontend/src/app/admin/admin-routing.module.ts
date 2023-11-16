import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListUserComponent} from "./pages/list-user/list-user.component";
import {AddUserComponent} from "./pages/add-user/add-user.component";
import {EditUserComponent} from "./pages/edit-user/edit-user.component";
import {ListAccountComponent} from "./pages/account/list-account/list-account.component";
import {AddAccountComponent} from "./pages/account/add-account/add-account.component";
import {EditAccountComponent} from "./pages/account/edit-account/edit-account.component";
import {ExcahngeRateComponent} from "./pages/excahnge-rate/excahnge-rate.component";
import {OperationsComponent} from "./pages/operations/operations.component";
import {MovementsComponent} from "./pages/reports/movements/movements.component";
import {FrozenComponent} from "./pages/reports/frozen/frozen.component";
import {TotalComponent} from "./pages/reports/total/total.component";
import {DetailComponent} from "./pages/reports/detail/detail.component";

const routes: Routes = [
  { path: 'user', children: [
      { path: 'list', component: ListUserComponent },
      { path: 'add', component: AddUserComponent },
      { path: 'edit/:id', component: EditUserComponent }
    ]
  },
  { path: 'account', children: [
      { path: 'list', component: ListAccountComponent },
      { path: 'add/:userId', component: AddAccountComponent },
      { path: 'edit/:id', component: EditAccountComponent }
    ]
  },
  { path: 'exchange', component: ExcahngeRateComponent },
  { path: 'operations', component: OperationsComponent },
  { path: 'reports', children: [
      { path: 'movements', component: MovementsComponent },
      { path: 'frozen', component: FrozenComponent },
      { path: 'total', component: TotalComponent},
      { path: 'detail', component: DetailComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
