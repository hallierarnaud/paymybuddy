import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
// ajout de l'import pour récupérer les userAccounts
import {UserAccount} from "../data/userAccount";

@Injectable()
export class AuthService {

  private createAccountUrl: string;

  constructor(private http: HttpClient) {
    this.createAccountUrl = 'http://localhost:9001/useraccounts';
  }

  // ajout de la méthode pour récupérer les userAccounts
  getUserAccount(email: string) {
    return this.http.get<UserAccount>('http://localhost:9001/useraccounts/'+email);
  }

}
