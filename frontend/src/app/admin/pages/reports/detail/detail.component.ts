import {Component, inject} from '@angular/core';
import {UserService} from "../../../../user/user.service";
import {JwtService} from "../../../../commons/services/jwt.service";
import {AccountTransaction} from "../../../../user/models/account-transaction";
import {AccountDetail} from "../../../../commons/models/AccountDetail";
import {TransactionRequest} from "../../../../commons/models/TransactionRequest";
import {NgForm} from "@angular/forms";
import {AdminService} from "../../../admin.service";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent {

  private adminService: AdminService = inject(AdminService);
  private jwtService: JwtService = inject(JwtService);
  account: AccountDetail;
  accounts: AccountDetail[];
  accountId: number;

  constructor() {
    this.account = new AccountDetail();
  }

  ngOnInit(): void {
    this.adminService.getAccounts().subscribe({
      next: (data: AccountDetail[]) => this.accounts = data,
      error: (error) => console.log(error)
    });
  }

  onSubmit(form: NgForm) {
    this.adminService.getDetailAccount(this.accountId).subscribe({
      next: (data: AccountDetail) => this.account = data,
      error: (error) => console.log(error)
    });
  }

}
