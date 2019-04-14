import { Component, OnInit } from '@angular/core';
import { MemberService } from '../services/member.service';
import { AlertController } from '@ionic/angular';
import { Member } from '../entities/member';
import { Router } from  "@angular/router";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  username: string;
  password: string;

  member: Member;

  constructor(private memberService: MemberService, private alertController: AlertController, private router : Router, private storage: Storage) {
  }

  ngOnInit() {
  }

  login() {
    this.memberService.login(this.username, this.password).subscribe(
      response =>{
        this.member = response.member;
        this.storage.set('isLogin', true);
        this.storage.set('currentCustomer', this.member);
        this.router.navigateByUrl('home');
      },
      error=> {
        this.presentAlert(error);
      }
    );
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: "ERROR: " + message.substring(37),
      buttons: ['OK']
    });
    await alert.present();
  }

}
