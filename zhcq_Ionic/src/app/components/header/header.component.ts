import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../../services/shoppingcart.service';
import { Member } from '../../entities/member';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';
import { ShoppingCart } from '../../entities/cart';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})

export class HeaderComponent implements OnInit {

  loggedIn: boolean;
  member: Member;
  cart: ShoppingCart;

  constructor(private cartService: ShoppingCartService, private alertController: AlertController, private storage: Storage) {
    storage.get('currentCustomer').then((data) => {
      this.member = data;
      this.checkstatus();
    });
  }

  ngOnInit() {
  }

  async checkstatus() {
    console.log('TEST FOR HEADER: ');
    console.log('member : ' + this.member);
    if (this.member !== undefined && this.member !== null) {
      this.loggedIn = true;
    } else {
      this.loggedIn = false;
    }
    console.log('Logged in status: ' + this.loggedIn);
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      message: message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
