import { Component, OnInit, ɵConsole } from '@angular/core';
import { NavController, NavParams, AlertController } from '@ionic/angular';
import { ProductEntity } from '../entities/product';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { CategoryService } from '../services/category.service';
import { Category } from '../entities/category';


@Component({
  selector: 'app-browse-products',
  templateUrl: './browse-products.page.html',
  styleUrls: ['./browse-products.page.scss'],
})
export class BrowseProductsPage implements OnInit {
  public searchControl: FormControl;

  errorMessage: string;
  retrievedProducts: ProductEntity[];
  products: ProductEntity[];
  catId: number;
  category: Category;

  constructor(private productService: ProductService, 
    private activatedRoute: ActivatedRoute, private alertController: AlertController, private categoryService: CategoryService) {
    this.searchControl = new FormControl();
    this.errorMessage = '';

    // this.images = this.navParams.get('images'); //get product image URIs
    // this.grid = Array(Math.ceil(this.images.length/2)); 
  }

  ngOnInit() {
    this.catId = parseInt(this.activatedRoute.snapshot.paramMap.get('catId'));
    console.log('CATEGORY ID IS: ' + this.catId);
    if (!isNaN(this.catId)) { // Localhost:8100/browse-products/id
      this.categoryService.retrieveCategoryById(this.catId).subscribe(
        response => {
          this.category = response.category;
          console.log('category retrieved = ' + this.category.categoryName);
        },
        error => {
          console.log('Entered Error Message');
          this.errorMessage = error;
          this.presentAlert(this.errorMessage.substring(37));
        }
      );
      this.productService.retrieveProductByCat(this.catId).subscribe(
        response => {

          this.retrievedProducts = response.products ;
          this.products = this.retrievedProducts;

          this.searchControl.valueChanges.pipe(debounceTime(700)).subscribe(search => {
              this.products = this.retrievedProducts;
              this.setFilteredItems(search);
          });
        },
        error => {
          this.errorMessage = error;
        }
      );
    } else { // Localhost:8100/browse-products
      this.productService.retrieveAllUniqueProducts().subscribe(
        response => {

          this.retrievedProducts = response.products ;
          this.products = this.retrievedProducts;

          this.searchControl.valueChanges.pipe(debounceTime(700)).subscribe(search => {
            this.products = this.retrievedProducts;
            this.setFilteredItems(search);
          });
        },
        error => {
          this.errorMessage = error;
          this.presentAlert(this.errorMessage.substring(37));
        }
      );
    }

  }

  setFilteredItems(searchTerm: string) {

    this.products = this.filterItems(searchTerm);
  }

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
      header: 'Error: ' + message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
