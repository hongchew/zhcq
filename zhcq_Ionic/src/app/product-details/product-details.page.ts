import { Component, OnInit } from '@angular/core';

import {AlertController} from '@ionic/angular';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { ProductEntity } from '../entities/product';
import { Storage } from '@ionic/storage';
import { Member } from '../entities/member';
import { ShoppingCart } from '../entities/cart';
import { ShoppingCartService } from '../services/shoppingcart.service';


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

  //For adding into cart
  member: Member
  cart: ShoppingCart;
  cartId: number 
  

  sliderOpts = {
    zoom: false,
    slidesPerView: 1.5,
    spaceBetween: true
  };

  wishlisted = false;

  constructor(private productService: ProductService, 
    private activatedRoute: ActivatedRoute, private alertController: AlertController, private storage: Storage, private shoppingCartService: ShoppingCartService) {
      storage.get('currentCustomer').then((data) => {
        this.member = data;
        console.log(this.member.firstName);
        console.log(this.member.lastName);
        console.log(this.member.username);
      });
     }

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
          this.presentAlert(error);
        }
      );
    }
    else
    {
      this.errorMessage = "No Product ID was provided";
      this.presentAlert(this.errorMessage);
    }
    
  }

  async wishListAlert() { //called when user adds to wish list. insert wish list logic here
    const alert = await this.alertController.create({
      header: 'added to wish list!'
    });
    await alert.present();
  }

  addToWishList() {
   this.wishlisted = !this.wishlisted;
  }


  async addToCart() {
    console.log("cartID = " + this.cartId)
    console.log("Product Id = " + this.id)
    const alert = await this.alertController.create({
      header: 'Added to Cart!'
    });
    this.shoppingCartService.addToCart(this.member.shoppingCart.cartId,this.id).subscribe(response => {
      console.log("response = " + response);
      alert.present();
    },
    error =>{
      this.presentAlert(error);
    }
    );
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      message: message,
      buttons: ['OK']
    });
    await alert.present();
  }
  
}
