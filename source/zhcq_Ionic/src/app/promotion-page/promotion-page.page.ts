import { Component, OnInit } from '@angular/core';
import { PromotionService } from '../services/promotion.service';
import { Promotion } from '../entities/promotion';
import { AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { ProductEntity } from '../entities/product';
import { THIS_EXPR, IfStmt } from '@angular/compiler/src/output/output_ast';
import { debounceTime } from 'rxjs/operators';



@Component({
  selector: 'app-promotion-page',
  templateUrl: './promotion-page.page.html',
  styleUrls: ['./promotion-page.page.scss'],
})
export class PromotionPagePage implements OnInit {

  public searchControl: FormControl;
  products: Array<ProductEntity> = [];
  retrievedProducts: Array<ProductEntity> = [];

  errorMessage = '';
  promotion = new Promotion();
  promotionId: number;
  endDate = '';


// tslint:disable: max-line-length
  constructor(private promotionService: PromotionService, private activatedRoute: ActivatedRoute, private alertController: AlertController) {
    this.searchControl = new FormControl();
   }

  ngOnInit() {
    this.promotionId = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
    console.log("promo ID: " + this.promotionId);
    this.promotionService.retrievePromotionById(this.promotionId).subscribe(
      response => {
        this.promotion = response.promotion;
        if (this.promotion != null) {
          this.endDate = this.promotion.endDate.toString();
          console.log('promo retrieved = ' + this.promotion.promotionName);
          this.retrievedProducts = this.promotion.promotionalProducts;
          this.products = this.retrievedProducts;
          // search bar
          this.searchControl.valueChanges.pipe(debounceTime(700)).subscribe(search => {
            this.products = this.retrievedProducts;
            this.setFilteredItems(search);
        });
        }

      },
      error => {
        this.errorMessage = error;
        this.presentAlert(this.errorMessage.substring(37));
      }
    );
   }
// search bar
   setFilteredItems(searchTerm: string) {
    if (searchTerm != null) {
      this.products = this.filterItems(searchTerm);
    }
  }
// search bar
  filterItems(searchTerm: string) {
    console.log('PRODUCTS ARRAY IN FILTER ITEMS METHOD: ' + this.products);

    return this.products.filter(product => {
      console.log('Item Name = ' + product.productName);
      console.log('Search Term = ' + searchTerm);

      return product.productName.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;
    });
  }

   async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'ERROR: ' + message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
