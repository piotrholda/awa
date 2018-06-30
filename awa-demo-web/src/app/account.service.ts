import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../environments/environment";
import {Router} from "@angular/router";
import {Account} from "./model/account";


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  static url = environment.hostUrl + '/account';

  static httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient,
              private router: Router) {
  }

  public register(account: Account) {
    console.log('register(' + JSON.stringify(account) + ')');
    this.http.post(AccountService.url, account, AccountService.httpOptions).subscribe(() => {
        this.router.navigate(['/accounts'])
    });
  }

}
