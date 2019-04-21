import { Component, OnInit } from '@angular/core';
import { MemberService } from '../services/member.service';
import { AlertController } from '@ionic/angular';
import { Member } from '../entities/member';
import { Router } from '@angular/router';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage = '';
  username: string;
  password: string;

  member: Member;

  constructor(private memberService: MemberService, private alertController: AlertController,
    private router: Router, private storage: Storage) {
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
        console.log("TEST FOR LOGGED IN ");
        console.log("Member = " + this.member);
      },
      error => {
        this.errorMessage =  error;
        this.presentAlert(this.errorMessage.substring(37));
      }
    );
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
