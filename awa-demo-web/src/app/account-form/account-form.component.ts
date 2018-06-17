import { Component, OnInit } from '@angular/core';
import {Account} from "../model/account";

@Component({
  selector: 'app-account-form',
  templateUrl: './account-form.component.html',
  styleUrls: ['./account-form.component.scss']
})
export class AccountFormComponent implements OnInit {

  account: Account;

  constructor() { }

  ngOnInit() {
    this.account = new Account();
  }

  onSubmit(account: Account) {

  }
}
