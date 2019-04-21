import { Component, OnInit, Input } from '@angular/core';
import { ModalController, NavParams } from '@ionic/angular';
import { SalestransactionService } from '../services/salestransaction.service';
import { Member } from '../entities/member';
import { SaleTransaction } from '../entities/saletransaction';
import { Storage } from '@ionic/storage';
import { ProductEntity } from '../entities/product';
import { Category } from '../entities/category';


@Component({
  selector: 'app-sizeguide',
  templateUrl: './sizeguide.page.html',
  styleUrls: ['./sizeguide.page.scss'],
})
export class SizeguidePage implements OnInit {
  errorMessage = '';
  member: Member;
  memberId: number;
  transactions: SaleTransaction[];
  products: Array<ProductEntity> = [];
  isLogin: boolean;
  product: ProductEntity;
  category: string;
  // "value" passed in componentProps
  @Input() value: string;

  constructor(private modalController: ModalController, private saleTransactionService: SalestransactionService, 
              private storage: Storage, private navparams: NavParams) {


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
          this.category = this.value;
          console.log('transaction Length= ' + this.transactions.length);
          console.log(this.transactions);

          for (let txn of this.transactions) {
            for (let item of txn.saleTransactionLineItems) {
              console.log('categoryName = ' + this.value);
              console.log('Product Category Name = ' + item.productEntity.productCategory.categoryName);

              if (item.productEntity.productCategory.categoryName === this.value ) {
                this.product = item.productEntity;
                console.log('product = ' + this.product);
                this.products.push(this.product);
              }
            }
          }
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
