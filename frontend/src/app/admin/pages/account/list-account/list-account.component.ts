import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../../admin.service";
import {Router} from "@angular/router";
import {AccountDetail} from "../../../../commons/models/AccountDetail";
import {User} from "../../../models/User";

@Component({
  selector: 'app-list-account',
  templateUrl: './list-account.component.html',
  styleUrls: ['./list-account.component.css']
})
export class ListAccountComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);
  accounts: AccountDetail[] = [];

  constructor() {}

  ngOnInit(): void {
    this.adminService.getAccounts().subscribe({
      next: (data: AccountDetail[]) => {
        data = data.filter(account => account.accountStatus != 'INACTIVE');
        this.accounts = data
      },
      error: err => console.log(err)
    });
  }

  editar(accountDetail: AccountDetail) {
    this.router.navigate([`/admin/account/edit`, accountDetail.accountId]);
  }

  eliminar(accountDetail: AccountDetail) {
    this.adminService.deleteAccount(accountDetail.accountId).subscribe({
      next: data => {
        this.ngOnInit();
      }
    });
  }

}
