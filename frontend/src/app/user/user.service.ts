import {inject, Injectable} from '@angular/core';
import {environment} from "../../environments/envoronment";
import {HttpClient} from "@angular/common/http";
import {AccountTransaction} from "./models/account-transaction";
import {TransactionRequest} from "../commons/models/TransactionRequest";
import {JwtService} from "../commons/services/jwt.service";
import {AccountDetail} from "../commons/models/AccountDetail";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURl = environment.baseUrl;

  private http: HttpClient = inject(HttpClient);
  private jwtService: JwtService = inject(JwtService);

  constructor() { }

  getUserTransactions(request: TransactionRequest){
    return this.http.post<AccountTransaction[]>(`${this.baseURl}/report/userTransactions`, request);
  }

  getAccoutsByUser(){
    const userId = this.jwtService.getClaim('id');
    return this.http.get<AccountDetail[]>(`${this.baseURl}/account/list/${userId}`);
  }
}
