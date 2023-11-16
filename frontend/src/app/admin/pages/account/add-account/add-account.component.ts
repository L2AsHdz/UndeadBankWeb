import {Component, inject, OnInit} from '@angular/core';
import {AdminService} from "../../../admin.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Account} from "../../../models/Account";
import {User} from "../../../models/User";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);
  account: Account = new Account();
  user: User = new User();

  constructor() {}

  ngOnInit(): void {
    this.user.userId = parseInt(this.route.snapshot.paramMap.get('userId') ?? '0');
    this.adminService.getUser(this.user.userId).subscribe({
      next: (user) => this.user = user,
      error: (err) => console.log(err)
    });
  }

  onSubmit(){
    this.account.statusClassification.classificationId = 11
    console.log(this.account);
    this.adminService.createAccount(this.user.userId, this.account).subscribe({
      next: (account) => {
        this.router.navigate(['/admin/account/list']);
      },
      error: (err) => console.log(err)
    });
  }

  backlist() {
    this.router.navigate(['/admin/user/list']);
  }

}
