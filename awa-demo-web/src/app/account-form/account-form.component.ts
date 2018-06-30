import { Component, OnInit } from '@angular/core';
import {Account} from "../model/account";
import {AccountService} from "../account.service";

@Component({
  selector: 'app-account-form',
  templateUrl: './account-form.component.html',
  styleUrls: ['./account-form.component.scss']
})
export class AccountFormComponent implements OnInit {

  account: Account;

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.account = new Account();
  }

  onSubmit(account: Account) {
    this.accountService.register(account);
  }
}
