import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { CategoryService } from './services/category.service';
import { Category } from './entities/category';
import { Storage } from '@ionic/storage';
import { AlertController } from '@ionic/angular';

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
    {
      title: 'Login',
      url: '/login',
      icon: 'checkmark'
    },
    {
      title: 'Register',
      url: '/register',
      icon: 'add'
    },
    {
      title: 'About Us',
      url: '/about-us',
      icon: 'help'
    },
    {
      title: 'SALE',
      url: '/promotional-products',
      icon: 'happy'
    }
    
  ];

  public categories: Category[];
  public errorMessage: string;
  public isLogin: boolean;

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private categoryService: CategoryService,
    private storage: Storage, 
    private alertController : AlertController
  ) {
    this.initializeApp();

    this.categoryService.retrieveAllCategories().subscribe(
      response => {
				this.categories = response.categories
			},
			error => {				
				this.errorMessage = error
			}
    );
    storage.get('isLogin').then((data) => {
      this.isLogin = data;
    });
  }

  initializeApp() {
    
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  logout() {
    this.isLogin = false;
    this.storage.set("isLogin", false);
    this.storage.set("currentCustomer", undefined);
    this.presentAlert("Logged out!");
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: "ERROR: " + message.substring(37),
      buttons: ['OK']
    });
    await alert.present();
  }
}
