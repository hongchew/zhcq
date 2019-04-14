import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { CategoryService } from './services/category.service';
import { Category } from './entities/category';

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

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private categoryService: CategoryService
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
  }

  initializeApp() {
    
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }
}
