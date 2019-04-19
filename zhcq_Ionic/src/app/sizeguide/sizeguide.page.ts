import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { SalestransactionService } from '../services/salestransaction.service';
import { Member } from '../entities/member';
import { SaleTransaction } from '../entities/saletransaction';
import { Storage } from '@ionic/storage';


@Component({
  selector: 'app-sizeguide',
  templateUrl: './sizeguide.page.html',
  styleUrls: ['./sizeguide.page.scss'],
})
export class SizeguidePage implements OnInit {
  errorMessage: string;
  member: Member;
  memberId: number;
  transactions: SaleTransaction[];
  isLogin: boolean;

  constructor(private modalController: ModalController, private saleTransactionService: SalestransactionService, private storage: Storage) {

  }

  ngOnInit() {
    this.storage.get('currentCustomer').then((data) => {
      this.member = data;
    });
    this.storage.get('isLogin').then((data) => {
      this.memberId = this.member.memberId;
      console.log('lOGGED IN CUSTOMER: ' + this.memberId);
      this.isLogin = data;
      this.saleTransactionService.retrieveSalesTransactionByUserId(this.memberId).subscribe(
        response => {
          this.transactions = response.salesTransactions;
          console.log('transaction Length= ' + this.transactions.length);
          console.log(this.transactions);
        },
        error => {
          console.log(error);
        }
      );
    });
  }

  closeModal() {
    this.modalController.dismiss();
  }
}
