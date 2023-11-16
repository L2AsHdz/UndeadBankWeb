import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../admin.service";
import {AccountType} from "../../models/AccountType";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-excahnge-rate',
  templateUrl: './excahnge-rate.component.html',
  styleUrls: ['./excahnge-rate.component.css']
})
export class ExcahngeRateComponent implements OnInit {

  private adminService: AdminService = inject(AdminService);
  accounts: AccountType[] = [];

  constructor() {}

  ngOnInit(): void {
    this.adminService.getAccountTypes().subscribe({
      next: (data: AccountType[]) => this.accounts = data,
      error: (error) => console.log(error)
    });
  }

  changeRate(accountType: AccountType) {
    Swal.fire({
      title: 'Ingrese el  nuevo valor',
      input: 'text',
      inputLabel: `Valor actual: ${accountType.exchangeRate}`,
      showCancelButton: true
    }).then(result => {
      if (result.isConfirmed) {
        if (!result.value)
          Swal.fire('Error', 'El nuevo valor no puede estar vacío', 'error');
        else {
          console.log(result.value)
          accountType.exchangeRate = result.value;
          console.log(accountType.exchangeRate)
          this.adminService.modifyExchangeRate(accountType.accountTypeDataId, accountType.exchangeRate)
            .subscribe({
              next: () => Swal.fire('Tipo de cambio actualizado', `El tipo de cambio de ${accountType.currency} ha sido actualizado`, 'success')
            });
        }
      }
    });
  }

}
