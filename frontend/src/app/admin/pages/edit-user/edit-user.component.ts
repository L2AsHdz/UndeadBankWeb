import {Component, inject, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {AdminService} from "../../admin.service";
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit{

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);
  user: User = new User();

  constructor() {}

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id');
    this.adminService.getUser(parseInt(userId!)).subscribe({
      next: (user) => this.user = user,
      error: (err) => console.log(err)
    });
  }

  onSubmit(){
    this.adminService.updateUser(this.user).subscribe({
      next: (user) => {
        this.backlist();
      },
      error: (err) => console.log(err)
    });
  }

  backlist() {
    this.router.navigate(['/admin/user/list']);
  }

}
