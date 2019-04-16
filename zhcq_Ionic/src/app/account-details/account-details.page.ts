import { Component, OnInit } from '@angular/core';
import { Member } from '../entities/member';
import { MemberService } from '../services/member.service';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';
import { SaleTransaction } from '../entities/saletransaction';
import { SalestransactionService } from '../services/salestransaction.service';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.page.html',
  styleUrls: ['./account-details.page.scss'],
})
export class AccountDetailsPage implements OnInit {
  errorMessage: string;
  member: Member;
  memberId : number;
  isLogin: boolean;
  transactions : SaleTransaction[];

  constructor(private memberService: MemberService, private storage: Storage, private alertController: AlertController, private saleTransactionService : SalestransactionService
              ) {
    storage.get('isLogin').then((data) => {
      this.isLogin = data;
    });
    storage.get('currentCustomer').then((data) => {
      this.member = data;
      this.memberId = this.member.memberId;
    });
  }

  ngOnInit() {
    // this.viewAccountDetails();
    console.log("Current member id: " + this.member.memberId);
    this.saleTransactionService.retrieveSalesTransactionByUserId(this.member.memberId).subscribe(
      response => {
        this.transactions = response.salesTransactions;
      },
      error => {
        this.errorMessage = error;
      }
    ) 
  }

  async logout() {

    const logoutSuccess = await this.alertController.create({
      header: 'Successfully Logged Out!',
      buttons: ['OK']
    });

    this.isLogin = false;
    this.storage.set("isLogin", false);
    this.storage.set("currentCustomer", undefined);
    logoutSuccess.present();

  }
  

}
