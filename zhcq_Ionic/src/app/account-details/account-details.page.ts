import { Component, OnInit } from '@angular/core';
import { Member } from '../entities/member';
import { MemberService } from '../services/member.service';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.page.html',
  styleUrls: ['./account-details.page.scss'],
})
export class AccountDetailsPage implements OnInit {
  member: Member;
  isLogin: boolean;

  constructor(private memberService: MemberService, private storage: Storage) { 
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

  // viewAccountDetails() {
  //   if (this.isLogin) {
  //     this.memberService.retrieveMember(this.member.memberId).subscribe(response => {
  //       console.log(response);
  //       this.member
  //     });
  //   }
  

}
