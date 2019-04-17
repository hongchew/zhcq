import { Component, OnInit } from '@angular/core';
import { Member } from '../entities/member';
import { MemberService } from '../services/member.service';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';
import { SaleTransaction } from '../entities/saletransaction';
import { SalestransactionService } from '../services/salestransaction.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.page.html',
  styleUrls: ['./account-details.page.scss'],
})
export class AccountDetailsPage implements OnInit {
  errorMessage: string;
  member: Member;
  memberId: number;
  isLogin: boolean;
  transactions: SaleTransaction[];

  constructor(private memberService: MemberService, private storage: Storage,
    private alertController: AlertController, private saleTransactionService: SalestransactionService,
    private router: Router) { }

  ngOnInit() {
  }

  ionViewWillEnter() {
    this.storage.get('currentCustomer').then((data) => {
      this.member = data;
      console.log('lOGGED IN CUSTOMER: ' + this.member);
    });
    this.storage.get('isLogin').then((data) => {
      this.isLogin = data;
      this.retrieveSalesTransactions();
    });
  }

  retrieveSalesTransactions() {
    if (this.isLogin) {
      this.memberId = this.member.memberId;
      console.log("member name = " + this.member.firstName);
      // this.viewAccountDetails();
      this.saleTransactionService.retrieveSalesTransactionByUserId(this.member.memberId).subscribe(
        response => {
          this.transactions = response.salesTransactions;
          console.log("transaction Length= " + this.transactions.length);
        },
        error => {
          this.errorMessage = error;
          this.presentAlert(this.errorMessage.substring(37));
        }
      );
    }
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: message,
      buttons: ['OK']
    });
    await alert.present();
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

    this.router.navigate(['/home']);


  }

}
