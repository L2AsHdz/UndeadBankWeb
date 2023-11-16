import {Component, inject} from '@angular/core';
import {JwtService} from "./commons/services/jwt.service";
import {AuthService} from "./auth/auth.service";
import {USER_ROL} from "./commons/models/USER_ROL";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  localStorage = localStorage;
  jwtService: JwtService = inject(JwtService);
  authService: AuthService = inject(AuthService);
  router: Router = inject(Router);

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  protected readonly USER_ROL = USER_ROL;
}
