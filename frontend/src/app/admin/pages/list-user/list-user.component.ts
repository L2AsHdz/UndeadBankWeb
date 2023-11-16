import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../admin.service";
import { Router } from '@angular/router';
import {User} from "../../models/User";

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);

  users: User[]= [];

  ngOnInit(): void {
    this.adminService.getUsers().subscribe({
      next: (data: User[]) => this.users = data,
      error: err => console.log(err)
    });
  }

  add() {
    this.router.navigate(['/admin/user/add']);
  }

  editar(user: User) {
    this.router.navigate([`/admin/user/edit`, user.userId]);
  }

  crearCuenta(user: User) {
    this.router.navigate([`/admin/account/add`, user.userId]);
  }

  // verCuentas(proyecto: Proyecto) {
  //   localStorage.setItem('idProyecto', proyecto.id.toString());
  //   this.router.navigate(['/actividad/list']);
  // }
}
