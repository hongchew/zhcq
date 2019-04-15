import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { CategoryService } from './services/category.service';
import { Category } from './entities/category';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';
import { CoordinatedOutfit } from './entities/outfit';
import { OutfitService } from './services/outfit.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {
  public appPages = [
    {
      title: 'Home',
      url: '/home',
      icon: 'home'
    },
    // {
    //   title: 'Login/Register',
    //   url: '/account-details',
    //   icon: 'add'
    // },
    {
      title: 'About Us',
      url: '/about-us',
      icon: 'help'
    },
    {
      title: 'SALE',
      url: '/promotional-products',
      icon: 'pricetags'
    },
    {
      title: 'Outfits',
      url: '/coordinated-outfits',
      icon: 'shirt'
    }
  ];

  public categories: Category[];
  public outfits: CoordinatedOutfit[];
  public errorMessage: string;
  public isLogin: boolean;

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private categoryService: CategoryService,
    private storage: Storage,
    private alertController: AlertController,
    private outfitService: OutfitService
  ) {
    this.initializeApp();

    this.categoryService.retrieveAllCategories().subscribe(
      response => {
        this.categories = response.categories;
      },
      error => {
        this.errorMessage = error;
      }
    );
    storage.get('isLogin').then((data) => {
      this.isLogin = data;
    });

    this.outfitService.retrieveAllOutfits().subscribe(
      response => {
        console.log(response);
        this.outfits = response.outfits;
      },
      error => {
        this.errorMessage = error;
        this.presentAlert(this.errorMessage.substring(37));
      }
    );
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  async logout() {

    const logoutSuccess = await this.alertController.create({
      header: 'Successfully Logged Out!',
      buttons: ['OK']
    });

    this.isLogin = false;
    this.storage.set("isLogin", false);
    this.storage.set("currentCustomer", undefined);
    logoutSuccess.present();

  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: "ERROR: " + message.substring(37),
      buttons: ['OK']
    });
    await alert.present();
  }
}
