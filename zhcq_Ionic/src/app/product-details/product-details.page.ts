import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AlertController} from '@ionic/angular';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.page.html',
  styleUrls: ['./product-details.page.scss'],
})
export class ProductDetailsPage implements OnInit {

  wishlisted = false;
  constructor(private router : Router, private alertController: AlertController) { }

  ngOnInit() {
  }

  async presentAlert() { //called when user adds to wish list. insert wish list logic here
    const alert = await this.alertController.create({
      header: 'added to wish list!'
    });
    await alert.present();
  }
  addToWishList() {
   this.wishlisted = !this.wishlisted;
  }
  
}
