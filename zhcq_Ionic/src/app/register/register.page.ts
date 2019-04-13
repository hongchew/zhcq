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

  newMember: Member;

  constructor(private memberService: MemberService, public alertController: AlertController) { }

  ngOnInit() {
  }

  registerMember() {
    console.log(this.firstName);
    console.log(this.lastName);
    console.log(this.username);
    console.log(this.password);
    if (this.password != this.confirmPassword) {
      this.presentAlert("Password does not match");
    } else {
      this.memberService.createMember(this.firstName, this.lastName, this.username, this.password).subscribe(
        response => {
          this.newMember = response.member;
          this.presentAlert("New Member " + this.newMember.memberId + " created successfully");
        },
        error => {
          this.presentAlert(error);
        }
      );
    }
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      message: message,
      buttons: ['OK']
    });
    await alert.present();
  }



}
