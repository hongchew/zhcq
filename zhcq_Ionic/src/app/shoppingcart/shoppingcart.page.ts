import { Component, OnInit } from '@angular/core';
import { Storage } from '@ionic/storage';
import { Member } from '../entities/member';
import { ProductEntity } from '../entities/product';
import { ShoppingCart } from '../entities/cart';
import { AlertController } from '@ionic/angular';
import { ShoppingCartService } from '../services/shoppingcart.service';

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.page.html',
  styleUrls: ['./shoppingcart.page.scss'],
})
export class ShoppingcartPage implements OnInit {

  member: Member;
  products: ProductEntity[];
  cart: ShoppingCart;
  quantity: number[];
  isLogin: boolean;

  constructor(private storage: Storage, private alertController: AlertController, private shoppingCartService: ShoppingCartService) {
    storage.get('currentCustomer').then((data) => {
      this.member = data;
    });
    storage.get('isLogin').then((data) => {
      this.isLogin = data;
    });
    this.viewCart();
  }

  ngOnInit() {
  }

  // ionViewWillEnter() {
  //   this.storage.get('currentCustomer').then((data) => {
  //     this.member = data;

  //     console.log("member username " + this.member.username);
  //     this.viewCart();
  //   });
  // }

  viewCart() {
    if (this.isLogin) {
      this.shoppingCartService.retrieveShoppingCart(this.member.memberId).subscribe(
        response => {
          this.cart = response.userShoppingCart;
          this.products = this.cart.products;
          this.quantity = this.cart.quantity;
        },
        error => {
          this.presentAlert(error);
        }
      );
    } else {
      this.presentAlert('You are not logged in!');
    }
  }

  async removeProduct(product: ProductEntity) {
    const alert = await this.alertController.create({
      header: 'Confirm',
      message: 'Remove Item? <strong>:-(</strong>',
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
            this.shoppingCartService.removeFromCart(this.cart.cartId, product.productId).subscribe(
              response => {
               const index:number = this.products.indexOf(product);
                if(index != -1) {
                  this.products.splice(index,1);
                  console.log("successfully removed product!");
                }
                this.presentAlert("Successfully removed product!");
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
      buttons: ['OK']
    });
    await alert.present();
  }


}
