import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
// Y: ajout de l'import pour récupérer les userAccounts
import {UserAccount} from "../data/userAccount";
import {Observable} from "rxjs";

@Injectable()
export class AuthService {

  // ajout du système d'authentification et de desauthentification
  isAuth = false;

  public signIn() {
    this.isAuth = true;
  }

  public signOut() {
    this.isAuth = false;
  }


  private createAccountUrl: string;

  // ajout d'une url
  private userAccountsUrl: string;

  constructor(private http: HttpClient) {
    this.createAccountUrl = 'http://localhost:9001/useraccounts/';
    this.userAccountsUrl = 'http://localhost:9001/useraccounts';
  }

  // Y: ajout de la méthode pour récupérer un userAccount avec son adresse mail
  public getUserAccount(email: string) {
    return this.http.get<UserAccount>(this.createAccountUrl+email);
  }

  // ajout de la méthode pour récupérer tous les userAccounts
  public getUserAccounts(): Observable<UserAccount[]> {
    return this.http.get<UserAccount[]>(this.userAccountsUrl);
  }

  // ajout de la méthode pour sauvegarder un useraccount
  public saveUserAccount(userAccount: UserAccount) {
    return this.http.post<UserAccount>('http://localhost:9001/useraccounts', userAccount);
  }

}
