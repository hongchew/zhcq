import { Component, OnInit } from '@angular/core';
import { NavController, NavParams } from '@ionic/angular';


@Component({
  selector: 'app-browse-products',
  templateUrl: './browse-products.page.html',
  styleUrls: ['./browse-products.page.scss'],
})
export class BrowseProductsPage implements OnInit {

  // images: Array<string>;  
  // grid: Array<Array<string>>;

  constructor() {
    // this.images = this.navParams.get('images'); //get product image URIs
    // this.grid = Array(Math.ceil(this.images.length/2)); 
  }
  ngOnInit() {
  }

  // ionViewLoaded() {

  //   let rowNum = 0; //counter to iterate over the rows in the grid
  
  //   for (let i = 0; i < this.images.length; i+=2) { //iterate images
  
  //     this.grid[rowNum] = Array(2); //declare two elements per row
  
  //     if (this.images[i]) { //check file URI exists
  //       this.grid[rowNum][0] = this.images[i] //insert image
  //     }
  
  //     if (this.images[i+1]) { //repeat for the second image
  //       this.grid[rowNum][1] = this.images[i+1]
  //     }
  
  //     rowNum++; //go on to the next row
  //   }
  
  // }

  // openProductPage(product){
  //   this.navCtrl.push('ProductDetails', {"product": product} );
  // }

}
