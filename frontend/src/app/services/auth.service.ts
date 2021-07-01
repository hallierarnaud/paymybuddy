import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserAccount} from "../data/userAccount";
import {Observable} from "rxjs";
import {Login} from "../data/login";

@Injectable()
export class AuthService {

  isAuth = false;
  private userAccountsUrl: string;

  constructor(private http: HttpClient) {
    this.userAccountsUrl = 'http://localhost:9001/useraccounts';
  }

  public signIn() {
    this.isAuth = true;
  }

  public signOut() {
    this.isAuth = false;
  }

  public saveUserAccount(userAccount: UserAccount) {
    return this.http.post<UserAccount>('http://localhost:9001/useraccounts', userAccount);
  }

  public checkLogin(login: Login) {
    return this.http.post<Login>('http://localhost:9001/checkuseraccounts', login);
  }

  public getUserAccountByEmail(email: string) {
    return this.http.get<UserAccount>('http://localhost:9001/getuseraccounts/'+email);
  }

  // conservation d'une méthode pour récupérer tous les userAccounts qui pourra servir pour les transactions
  public getUserAccounts(): Observable<UserAccount[]> {
    return this.http.get<UserAccount[]>(this.userAccountsUrl);
  }

}
