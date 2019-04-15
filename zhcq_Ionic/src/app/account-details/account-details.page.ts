import { Component, OnInit } from '@angular/core';
import { Member } from '../entities/member';
import { MemberService } from '../services/member.service';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.page.html',
  styleUrls: ['./account-details.page.scss'],
})
export class AccountDetailsPage implements OnInit {
  member: Member;
  isLogin: boolean;

  constructor(private memberService: MemberService, private storage: Storage, private alertController: AlertController,
              ) {
    storage.get('isLogin').then((data) => {
      this.isLogin = data;
    });
    storage.get('currentCustomer').then((data) => {
      this.member = data;
    });
  }

  ngOnInit() {
    // this.viewAccountDetails();

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
