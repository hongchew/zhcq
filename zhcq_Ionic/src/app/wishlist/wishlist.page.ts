import { Component, OnInit } from '@angular/core';
import { Storage } from '@ionic/storage';
import { Member } from '../entities/member';
import { ProductEntity } from '../entities/product';
import { WishList } from '../entities/wishlist';
import { AlertController } from '@ionic/angular';
import { WishListService } from '../services/wishlist.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.page.html',
  styleUrls: ['./wishlist.page.scss'],
})
export class WishlistPage implements OnInit {
    member: Member;
    products: ProductEntity[];
    wishlist: WishList;
    exists: boolean;
    isLogin: boolean;


    constructor(private storage: Storage, private alertController: AlertController, private wishListService: WishListService,
               private router: Router) {

    }

  ngOnInit() {
  }

  ionViewWillEnter() {
    this.storage.get('currentCustomer').then((data) => {
      this.member = data;
    });
    this.storage.get('isLogin').then((data) => {
      this.isLogin = data;
      this.viewWishList();
    });
  }

  viewWishList() {
    if (this.isLogin) {
      this.wishListService.retrieveWishList(this.member.memberId).subscribe(
        response => {
          this.wishlist = response.wishlist;
          if (this.wishlist.productEntities.length > 0) {
            this.exists = true;
          }
          this.products = this.wishlist.productEntities;
          console.log('LENGTH OF PRODUCTS = ' + this.products.length)
        },
        error => {
          this.presentAlert(error);
        }
      );
    } else {
      this.presentAlert('You are not logged in!');
      this.router.navigate(['/home']);
    }
  }

  async removeProduct(product: ProductEntity) {
    const alert = await this.alertController.create({
      header: 'Confirm',
      message: 'Remove Item? <ion-icon ios="ios-sad" md="md-sad"></ion-icon>',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          cssClass: 'secondary',
          handler: () => {
            console.log('Cancelled remove Product');
          }
        }, {
          text: 'Confirm',
          handler: () => {
            console.log("attempt to remove product");
            this.wishListService.removeFromWishList(this.member.memberId, product.productId).subscribe(
              response => {
               const index:number = this.products.indexOf(product);
                if(index != -1) {
                  this.products.splice(index,1);
                  console.log("successfully removed product!");
                }
                this.presentAlert("Item removed from Wish List!");
              },
              error => {
                this.presentAlert(error);
              }
            );
          }
        }
      ]
    });

    await alert.present();
  }


  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      message: message,
      buttons: [{
        text: 'OK'
      }]
    });
    await alert.present();
  }

 


}
