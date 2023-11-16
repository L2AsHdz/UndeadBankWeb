import {Component, inject} from '@angular/core';
import {AccountTransaction} from "../../../../user/models/account-transaction";
import {TransactionRequest} from "../../../../commons/models/TransactionRequest";
import {NgForm} from "@angular/forms";
import {AdminService} from "../../../admin.service";

@Component({
  selector: 'app-movements',
  templateUrl: './movements.component.html',
  styleUrls: ['./movements.component.css']
})
export class MovementsComponent {

  private adminService: AdminService = inject(AdminService);
  transacciones: AccountTransaction[];
  request: TransactionRequest;

  constructor() {
    this.request = new TransactionRequest()
  }

  onSubmit(form: NgForm) {
    console.log(this.request)
    this.adminService.getTransactions(this.request).subscribe({
      next: (data: AccountTransaction[]) => this.transacciones = data,
      error: (error) => console.log(error)
    });
  }

}
