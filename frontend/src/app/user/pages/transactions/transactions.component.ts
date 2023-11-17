import {Component, inject, OnInit} from '@angular/core';
import {AccountTransaction} from "../../models/account-transaction";
import {UserService} from "../../user.service";
import {TransactionRequest} from "../../../commons/models/TransactionRequest";
import {AccountDetail} from "../../../commons/models/AccountDetail";
import {JwtService} from "../../../commons/services/jwt.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  private userService: UserService = inject(UserService);
  private jwtService: JwtService = inject(JwtService);
  transacciones: AccountTransaction[];
  accounts: AccountDetail[];
  request: TransactionRequest;

  constructor() {
    this.request = new TransactionRequest()
  }

  ngOnInit(): void {
    this.userService.getAccoutsByUser().subscribe({
      next: (data: AccountDetail[]) => {
        console.log(data);
        this.accounts = data;
      },
      error: (error) => console.log(error)
    });
  }

  onSubmit(form: NgForm) {
    this.request.userId = this.jwtService.getClaim('id');

    console.log(this.request)
    this.userService.getUserTransactions(this.request).subscribe({
      next: (data: AccountTransaction[]) => this.transacciones = data,
      error: (error) => console.log(error)
    });
  }

  getFormatedValue(transaction: AccountTransaction, amount: number){
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

  getTotal(type: string) {
    if (this.transacciones){
      let t = this.transacciones[0];
      let amount = this.transacciones
        .filter(t => t.operationType == type)
        .map(t => t.amount)
        .reduce((a, b) => a + b);

      return this.getFormatedValue(t, amount);
    }
    return ''
  }
}
