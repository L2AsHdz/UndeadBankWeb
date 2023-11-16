import {inject, Injectable} from '@angular/core';
import {environment} from "../../environments/envoronment";
import {HttpClient} from "@angular/common/http";
import {JwtService} from "../commons/services/jwt.service";
import {User} from "./models/User";
import {AccountDetail} from "../commons/models/AccountDetail";
import {Account} from "./models/Account";
import {AccountType} from "./models/AccountType";
import {OperationRequest} from "./models/OperationRequest";
import {AccountTransaction} from "../user/models/account-transaction";
import {TransactionRequest} from "../commons/models/TransactionRequest";
import {FrozenAccounts} from "./models/FrozenAccount";
import {StateAccounts} from "./models/StateAccounts";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseURl = environment.baseUrl;

  private http: HttpClient = inject(HttpClient);
  private jwtService: JwtService = inject(JwtService);

  constructor() { }

  getUsers(){
    return this.http.get<User[]>(`${this.baseURl}/user`);
  }

  getUser(id: number){
    return this.http.get<User>(`${this.baseURl}/user/${id}`);
  }

  createUser(user: User){
    return this.http.post(`${this.baseURl}/user`, user);
  }

  updateUser(user: User){
    return this.http.put<User>(`${this.baseURl}/user/${user.userId}`, user);
  }

  getAccounts(){
    return this.http.get<AccountDetail[]>(`${this.baseURl}/account`);
  }

  getAccount(id: number){
    return this.http.get<Account>(`${this.baseURl}/account/${id}`);
  }

  createAccount(userId: number, account: Account){
    return this.http.post<Account>(`${this.baseURl}/account/${userId}`, account);
  }

  updateAccount(account: Account){
    return this.http.put<Account>(`${this.baseURl}/account/${account.accountId}`, account);
  }

  deleteAccount(accountId: number){
    return this.http.delete(`${this.baseURl}/account/${accountId}`);
  }

  getAccountTypes(){
    return this.http.get<AccountType[]>(`${this.baseURl}/account-type`);
  }

  modifyExchangeRate(exchangeId: number, exchangeRate: number){
    return this.http.put(`${this.baseURl}/account-type/exchange/${exchangeId}/${exchangeRate}`, null);
  }

  deposit(operationRequest: OperationRequest){
    return this.http.post(`${this.baseURl}/operation/deposit`, operationRequest);
  }

  withdraw(operationRequest: OperationRequest){
    return this.http.post(`${this.baseURl}/operation/withdraw`, operationRequest);
  }

  getTransactions(request: TransactionRequest){
    return this.http.post<AccountTransaction[]>(`${this.baseURl}/report/adminTransactions`, request);
  }

  getFrozenAccounts(){
    return this.http.get<FrozenAccounts[]>(`${this.baseURl}/report/frozenAccounts`);
  }

  getDetailAccount(accountId: number){
    return this.http.get<AccountDetail>(`${this.baseURl}/report/accountDetail/${accountId}`);
  }

  getTotalAccounts(){
    return this.http.get<StateAccounts>(`${this.baseURl}/report/accountsByState`);
  }
}
