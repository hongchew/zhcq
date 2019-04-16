import { Component, OnInit } from '@angular/core';
import { Member } from '../entities/member';
import { MemberService } from '../services/member.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})

export class RegisterPage implements OnInit {


  firstName: string;
  lastName: string;
  username: string;
  password: string;
  confirmPassword: string;
  email: string;

  newMember: Member;

  constructor(private memberService: MemberService, public alertController: AlertController) { }

  ngOnInit() {
  }

  registerMember() {
    console.log(this.firstName);
    console.log(this.lastName);
    console.log(this.username);
    console.log(this.password);
    console.log(this.email);
    if (this.password != this.confirmPassword) {
      this.presentAlert("Password does not match");
    } else {
      this.memberService.createMember(this.firstName, this.lastName, this.username, this.password, this.email).subscribe(
        response => {
          this.newMember = response.member;
          this.presentAlert("Account created!");
        },
        error => {
          this.presentAlert(error.substring(37));
        }
      );
    }
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: "ERROR: " + message,
      buttons: ['OK']
    });
    await alert.present();
  }



}
