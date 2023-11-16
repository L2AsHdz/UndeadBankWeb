import {Component, inject, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-manager-homepage',
  template: ``,
  styles: [``]
})
export class ManagerHomepageComponent implements OnInit {

  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
    constructor() { }

    ngOnInit(): void {
      if (this.authService.isAuthenticated()) {
        this.router.navigate(['/login']);
      }
      this.router.navigate(['/home']);
    }

}
