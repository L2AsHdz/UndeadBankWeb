import {Component, inject} from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../../user/user.service";
import {AdminService} from "../../admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {

  private adminService: AdminService = inject(AdminService);
  private router: Router = inject(Router);
  user: User = new User();

  onSubmit(){
    this.user.userClassification.classificationId = 7;
    this.adminService.createUser(this.user).subscribe({
      next: () => this.backList(),
      error: (err) => console.log(err)
    });
  }

  backList(): void {
    this.router.navigate(['/admin/user/list']);
  }

}
