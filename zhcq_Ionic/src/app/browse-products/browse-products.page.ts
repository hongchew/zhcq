import { Component, OnInit } from '@angular/core';
import { NavController, NavParams, AlertController } from '@ionic/angular';
import { ProductEntity } from '../entities/product';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-browse-products',
  templateUrl: './browse-products.page.html',
  styleUrls: ['./browse-products.page.scss'],
})
export class BrowseProductsPage implements OnInit {
  errorMessage: string;
  public products: ProductEntity[];
  catId: number;

  // images: Array<string>;
  // grid: Array<Array<string>>;

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute, private alertController: AlertController) {
    // this.images = this.navParams.get('images'); //get product image URIs
    // this.grid = Array(Math.ceil(this.images.length/2)); 
  }
  ngOnInit() {
    this.catId = parseInt(this.activatedRoute.snapshot.paramMap.get('catId'));
    console.log('CATEGORY ID IS: ' + this.catId);

    if (!isNaN(this.catId)) {
      this.productService.retrieveProductByCat(this.catId).subscribe(
        response => {
          // console.log(response);
          this.products = response.products ;
        },
        error => {
          this.errorMessage = error;
        }
      );
    } else {
      this.productService.retrieveAllUniqueProducts().subscribe(
        response => {
          console.log(response);
          this.products = response.products;
        },
        error => {
          this.errorMessage = error;
          this.presentAlert(this.errorMessage.substring(37));
        }
      );
    }
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'Error: ' + message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
