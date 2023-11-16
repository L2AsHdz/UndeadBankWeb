import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../../admin.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Account} from "../../../models/Account";

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);
  account: Account = new Account();

  constructor() {}

  ngOnInit(): void {
    const accountId = this.route.snapshot.paramMap.get('id');
    this.adminService.getAccount(parseInt(accountId!)).subscribe({
      next: (account) => this.account = account,
      error: (err) => console.log(err)
    });
  }

  onSubmit(){
    this.adminService.updateAccount(this.account).subscribe({
      next: (account) => {
        this.backlist();
      },
      error: (err) => console.log(err)
    });
  }

  backlist() {
    this.router.navigate(['/admin/account/list']);
  }

}
