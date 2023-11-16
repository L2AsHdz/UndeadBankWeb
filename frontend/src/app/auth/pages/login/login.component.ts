import {Component, inject} from '@angular/core';
import {NgForm} from "@angular/forms";
import {Credentials} from "../../models/credentials";
import {AuthService} from "../../auth.service";
import {Router} from "@angular/router";
import {JwtService} from "../../../commons/services/jwt.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private authService: AuthService = inject(AuthService);
  private jwtService: JwtService = inject(JwtService);
  private router: Router = inject(Router);

  credentials: Credentials;

  constructor() {
    this.credentials = new Credentials();
  }

  onSummit(form: NgForm) {
    console.log(form.value);
    this.credentials = form.value;

    this.authService.login(this.credentials)
      .subscribe({
        next: response => {
          console.log("Response", response);
          localStorage.setItem('token', response.token);
          console.log("Token", this.jwtService.decodeToken(response.token));
          this.router.navigate(['/home']);
        },
        error: error => console.log("Error", error.error)
      });
  }
}
