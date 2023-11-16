import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../../admin.service";
import {FrozenAccounts} from "../../../models/FrozenAccount";

@Component({
  selector: 'app-frozen',
  templateUrl: './frozen.component.html',
  styleUrls: ['./frozen.component.css']
})
export class FrozenComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  frozenAccounts: FrozenAccounts[];

  constructor() {}

  ngOnInit(): void {
    this.adminService.getFrozenAccounts().subscribe({
      next: (data: FrozenAccounts[]) => this.frozenAccounts = data,
      error: (error) => console.log(error)
    });
  }

}
