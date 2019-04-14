import { Component, OnInit } from '@angular/core';

import {AlertController, ModalController} from '@ionic/angular';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { ProductEntity } from '../entities/product';
import { Storage } from '@ionic/storage';
import { Member } from '../entities/member';
import { ShoppingCart } from '../entities/cart';
import { ShoppingCartService } from '../services/shoppingcart.service';
import { WishListService } from '../services/wishlist.service';
import { SizeguidePageModule } from '../sizeguide/sizeguide.module';
import { SizeguidePage } from '../sizeguide/sizeguide.page';

 
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
  onPromotion: boolean;
  promotionalPrice: any;

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

  constructor(private productService: ProductService, private modalController:ModalController,
    private activatedRoute: ActivatedRoute, private alertController: AlertController, private storage: Storage, private shoppingCartService: ShoppingCartService, private wishListService: WishListService) {
      storage.get('currentCustomer').then((data) => {
        this.member = data;
        console.log(this.member.firstName);
        console.log(this.member.lastName);
        console.log(this.member.username);
      });
     }

  ngOnInit() {
    this.id = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
    console.log('Selected productid = ' + this.id);

    if (!isNaN(this.id))
		{
      this.productService.retrieveProductById(this.id).subscribe(
        response => {
          console.log('response = '+response);
          this.selectedProduct = response.selectedProduct;
          this.diffColours = response.diffColours;
          this.diffSizes = response.diffSizes;
          this.suggestedProducts = response.suggestedProducts;

          // Check for promotion
          if(this.selectedProduct.promotion != null){
            this.onPromotion = true;
            this.promotionalPrice = ((1-this.selectedProduct.promotion.discountRate) * this.selectedProduct.unitPrice);
            console.log('Product Price is: ' + this.selectedProduct.unitPrice);
            console.log('Promotional rate is: ' + this.selectedProduct.promotion.discountRate);
            console.log('Calculated Promotional Price is: ' + this.promotionalPrice);
          }
        },
        error => {
          this.presentAlert(error.substring(37));
        }
      );
    } else {
      this.errorMessage = 'No Product ID was provided';
      this.presentAlert(this.errorMessage);
    }
  }

  async addToWishList() {
    // console.log('Add to Wishlist: MemberId = ' + this.member.memberId);
    // console.log('Add to Wishlist: ProductId = ' + this.id);

    const listSuccess = await this.alertController.create({
      header: 'added to wish list!'
    });
    if (this.member !== null && this.cartId !== undefined ) {
      this.wishListService.addToWishList(this.member.memberId, this.id).subscribe(
        response => {
          console.log('response = ' + response);
          listSuccess.present();
      },
      error => {
        this.presentAlert(error.substring(37));
        // this.ngOnInit();
      }
      );
    } else {
      this.presentAlert('Please Login to perform operation!');
    }
  }


  async addToCart() {
    // console.log('cartID = ' + this.cartId)
    // console.log('Product Id = ' + this.id)

    const cartAlert = await this.alertController.create({
      header: 'Added to Cart!'
    });

    if (this.member !== null && this.cartId !== undefined ) {
      this.shoppingCartService.addToCart(this.member.shoppingCart.cartId,this.id).subscribe(response => {
        console.log('response = ' + response);
        cartAlert.present();
      },
      error => {
        this.presentAlert('ERROR FROM ADDING TO CART: ' + error.substring(37));
        // this.ngOnInit();
      }
      );
    } else {
      this.presentAlert('Please Login to perform operation!');
    }
  }

  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'Error: ' + message,
      buttons: ['OK']
    });
    await alert.present();
  }


  async openSizeGuide() {
    const modal = await this.modalController.create({
      component: SizeguidePage
    })
    modal.present();
  }
  
}
