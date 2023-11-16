import { NgModule } from '@angular/core';
import {mapToCanActivate, RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "./commons/pages/not-found/not-found.component";
import {HomeComponent} from "./pages/home/home.component";
import {AuthGuard} from "./commons/auth.guard";
import {USER_ROL} from "./commons/models/USER_ROL";
import {ManagerHomepageComponent} from "./commons/components/manager-homepage.component";

const routes: Routes = [
  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'homepage', component: ManagerHomepageComponent},
  {
    path: 'home',
    component: HomeComponent,
    data: { requireAuthentication: true },
    canActivate: mapToCanActivate([AuthGuard])
  },
  { path: 'login', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) },
  {
    path: 'user',
    data: { requireAuthentication: true, requiredRol: USER_ROL.CUSTOMER },
    canActivate: mapToCanActivate([AuthGuard]),
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  },
  {
    path: 'admin',
    data: { requireAuthentication: true, requiredRol: USER_ROL.ADMIN },
    canActivate: mapToCanActivate([AuthGuard]),
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  { path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
