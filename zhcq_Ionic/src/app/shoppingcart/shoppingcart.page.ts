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

  constructor(private storage: Storage, private alertController: AlertController, private cartService: ShoppingCartService) {
    storage.get('currentCustomer').then((data) => {
      this.member = data;
      console.log(this.member.firstName);
      console.log(this.member.lastName);
      console.log(this.member.username);
    });
  }

  ngOnInit() {
    if (this.member == undefined) {
       console.log("User is not logged in!");
    } else {
      this.cartService.retrieveShoppingCart(this.member.memberId).subscribe(
        response => {
          this.cart = response.userShoppingCart;
          this.products = this.cart.products;
        },
        error => {
          this.presentAlert(error);
        }
      )
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
