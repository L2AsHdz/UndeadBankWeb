import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../admin.service";
import {OperationRequest} from "../../models/OperationRequest";
import {AccountDetail} from "../../../commons/models/AccountDetail";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent implements OnInit {

  private adminService: AdminService = inject(AdminService);
  deposit: boolean = false;
  operation: OperationRequest = new OperationRequest();
  accounts: AccountDetail[] = [];

  onSubmit(){
  console.log(this.deposit)
    console.log(this.operation)
    if(this.deposit){
      this.adminService.deposit(this.operation).subscribe({
        next: () => {
          Swal.fire('Deposito realizado', `Se realizo un deposito por ${this.operation.amount} a la cuenta ${this.operation.destinationAccount}`, 'success')
          this.operation = new OperationRequest();
        },
        error: err => console.log(err)
      });
    }
    else {
      this.adminService.withdraw(this.operation).subscribe({
        next: () => {
          Swal.fire('Retiro realizado', `Se realizo un retiro por ${this.operation.amount} a la cuenta ${this.operation.destinationAccount}`, 'success')
          this.operation = new OperationRequest();
        },
        error: err => console.log(err)
      });
    }
  }

  ngOnInit(): void {
    this.adminService.getAccounts().subscribe({
      next: (data: AccountDetail[]) => {
        data = data.filter(account => account.accountStatus != 'INACTIVE');
        this.accounts = data
      },
      error: err => console.log(err)
    });
  }

}
