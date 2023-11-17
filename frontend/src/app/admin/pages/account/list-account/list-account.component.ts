import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../../admin.service";
import {Router} from "@angular/router";
import {AccountDetail} from "../../../../commons/models/AccountDetail";
import {User} from "../../../models/User";
import {AccountTransaction} from "../../../../user/models/account-transaction";

@Component({
  selector: 'app-list-account',
  templateUrl: './list-account.component.html',
  styleUrls: ['./list-account.component.css']
})
export class ListAccountComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);
  accounts: AccountDetail[] = [];
  accounts2: AccountDetail[] = [];

  constructor() {}

  ngOnInit(): void {
    this.adminService.getAccounts().subscribe({
      next: (data: AccountDetail[]) => {
        this.accounts = data.filter(account => account.accountStatus != 'INACTIVE');
        this.accounts2 = data.filter(account => account.accountStatus != 'ACTIVE');
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

  getFormatedValue(transaction: AccountDetail, amount: number){
    switch (transaction.accountType){
      case 'BASIC':
        return ` Q. ${amount}`;
      case 'PREMIUM':
        return ` $. ${amount}`;
      case 'PLUS':
        return ` €. ${amount}`;
      default: return `Q. ${amount}`;
    }
  }

}
