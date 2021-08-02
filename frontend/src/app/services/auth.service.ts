import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserAccount} from "../data/userAccount";
import {Observable} from "rxjs";
import {Login} from "../data/login";
import {Contact} from "../data/contact";
import {Transaction} from "../data/transaction";
import {BankTransfer} from "../data/bankTransfer";

@Injectable()
export class AuthService {

  isAuth = false;
  internalTransactionsUrl: string;

  constructor(private http: HttpClient) {
    this.internalTransactionsUrl = 'http://localhost:9001/internaltransactions/';
  }

  public signIn() {
    this.isAuth = true;
  }

  public signOut() {
    this.isAuth = false;
  }

  public checkLogin(login: Login) {
    return this.http.post<Login>('http://localhost:9001/checkuseraccounts', login);
  }

  public getUserAccountByEmail(email: string) {
    let parameter = new HttpParams().set('email', email);
    return this.http.get<UserAccount>('http://localhost:9001/getuseraccounts', {params: parameter});
  }

  public saveUserAccount(userAccount: UserAccount) {
    return this.http.post<UserAccount>('http://localhost:9001/useraccounts', userAccount);
  }

  public getContactsByUserId(userId: string): Observable<Contact[]> {
    let parameter = new HttpParams().set('userId', userId);
    return this.http.get<Contact[]>('http://localhost:9001/contacts', {params: parameter});
  }

  public saveConnection(contact: Contact) {
    return this.http.post<Contact>('http://localhost:9001/contacts', contact);
  }

  public getTransactionsByInternalAccountId(internalAccountId: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.internalTransactionsUrl+internalAccountId);
  }

  public saveTransaction(transaction: Transaction) {
    return this.http.post<Transaction>('http://localhost:9001/internaltransactions', transaction);
  }

  public saveBankTransfer(bankTransfer: BankTransfer) {
    return this.http.post<BankTransfer>('http://localhost:9001/externaltransactions', bankTransfer);
  }
}
