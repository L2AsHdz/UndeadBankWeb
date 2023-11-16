import {inject, Injectable} from '@angular/core';
import {environment} from "../../environments/envoronment";
import {HttpClient} from "@angular/common/http";
import {Credentials} from "./models/credentials";
import {Token} from "./models/token";
import {JwtService} from "../commons/services/jwt.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseURl = environment.baseUrl;

  private http: HttpClient = inject(HttpClient);
  private jwtService: JwtService = inject(JwtService);

  constructor() {
  }

  getToken() {
    let token = localStorage.getItem('token');
    return token ? `Bearer ${token}` : null;
  }

  login(credentials: Credentials) {
    return this.http.post<Token>(`${this.baseURl}/auth/signin`, credentials);
  }

  isAuthenticated() {
    let token = localStorage.getItem('token');
    return token && !this.jwtService.isTokenExpired(token);
  }

  hasRequiredRol(rol: string): boolean {
    let token = localStorage.getItem('token');
    if (!token) return false;

    let user = this.jwtService.decodeToken(token);
    return user.rol === rol;
  }
}
