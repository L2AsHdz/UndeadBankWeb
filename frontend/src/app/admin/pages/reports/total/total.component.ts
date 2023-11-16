import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../../admin.service";
import {JwtService} from "../../../../commons/services/jwt.service";
import {AccountDetail} from "../../../../commons/models/AccountDetail";
import {NgForm} from "@angular/forms";
import {StateAccounts} from "../../../models/StateAccounts";

@Component({
  selector: 'app-total',
  templateUrl: './total.component.html',
  styleUrls: ['./total.component.css']
})
export class TotalComponent implements OnInit {

  private adminService: AdminService = inject(AdminService);
  total: StateAccounts = new StateAccounts();

  constructor() {
  }

  ngOnInit(): void {
    this.adminService.getTotalAccounts().subscribe({
      next: (data: StateAccounts) => this.total = data,
      error: (error) => console.log(error)
    });
  }

}
