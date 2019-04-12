import { Component, OnInit } from '@angular/core';

import {AlertController} from '@ionic/angular';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { ProductEntity } from '../entities/product';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.page.html',
  styleUrls: ['./product-details.page.scss'],
})
export class ProductDetailsPage implements OnInit {

  errorMessage: string;
  id: number;
  selectedProduct: ProductEntity;
  diffColours: ProductEntity[];
  diffSizes: ProductEntity[];
  suggestedProducts: ProductEntity[];

  wishlisted = false;

  constructor(private productService: ProductService, 
    private activatedRoute: ActivatedRoute, private alertController: AlertController) { }

  ngOnInit() {
    this.id = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
    console.log("Selected productid = " + this.id);

    if(!isNaN(this.id))
		{
      this.productService.retrieveProductById(this.id).subscribe(
        response => {
          console.log("response = "+response);
          this.selectedProduct = response.selectedProduct;
          this.diffColours = response.diffColours;
          this.diffSizes = response.diffSizes;
          this.suggestedProducts = response.suggestedProducts;
        },
        error => {
          this.errorMessage = error
        }
      );
    }
    else
    {
      this.errorMessage = "No Product ID was provided";
    }
    
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
