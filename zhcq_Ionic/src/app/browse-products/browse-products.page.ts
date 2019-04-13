import { Component, OnInit } from '@angular/core';
import { NavController, NavParams } from '@ionic/angular';
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
  
  public url= "images/bardot_top_black.jpg";
 
  // images: Array<string>;  
  // grid: Array<Array<string>>;

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute) {
    // this.images = this.navParams.get('images'); //get product image URIs
    // this.grid = Array(Math.ceil(this.images.length/2)); 
  }
  ngOnInit() {
    this.catId = parseInt(this.activatedRoute.snapshot.paramMap.get('catId'));
    console.log("CATEGORY ID IS: "+ this.catId);

    if(!isNaN(this.catId))
		{
      this.productService.retrieveProductByCat(this.catId).subscribe(
        response => {
          //console.log(response);
          this.products = response.products 
          
        },
        error => {
          this.errorMessage = error 
        }
      );
    } 
    else 
    {
      this.productService.retrieveAllProducts().subscribe(
        response => {
          console.log(response);
          this.products = response.products 
        },
        error => {
          this.errorMessage = error 
        }
      );
    }
  }

}
