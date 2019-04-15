import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../../services/shoppingcart.service';
import { Member } from '../../entities/member';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';
import { ShoppingCart } from '../../entities/cart';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent implements OnInit {

  loggedIn: boolean;
  member: Member;
  cart: ShoppingCart;

  constructor(private cartService: ShoppingCartService, private alertController: AlertController, private storage: Storage) {
    storage.get('currentCustomer').then((data) => {
      this.member = data;
    });
    storage.get('isLogin').then((data) => {
      this.loggedIn = data;
    });
  }

  ngOnInit() {
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      message: message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
