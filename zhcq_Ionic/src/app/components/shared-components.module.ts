import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { IonicModule } from '@ionic/angular';
import { FooterComponent } from './footer/footer.component';
// import { RouterModule, Routes } from '@angular/router';
// import { BrowseProductsPage } from '../browse-products/browse-products.page';
// import { ShoppingcartPage } from '../shoppingcart/shoppingcart.page';
// import { WishlistPage } from '../wishlist/wishlist.page';
// import { UserinfoPage } from '../userinfo/userinfo.page';

// const routes: Routes = [
//   {
//     path: 'viewProducts',
//     component: BrowseProductsPage
//   },
//   {
//     path: 'shoppingCart',
//     component: ShoppingcartPage
//   },
//   {
//     path: 'wishList',
//     component: WishlistPage
//   },
//   {
//     path: 'user',
//     component: UserinfoPage
//   }
// ];

@NgModule({
  declarations: [HeaderComponent, FooterComponent],
  imports: [
    CommonModule,
    IonicModule
    // RouterModule.forChild(routes),
  ],
  exports: [HeaderComponent,FooterComponent]
})
export class SharedComponentsModule { }
