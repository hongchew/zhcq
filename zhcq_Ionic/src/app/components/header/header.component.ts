import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from 'src/app/services/shoppingcart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {

  constructor(shoppingCartService: ShoppingCartService) { }

  ngOnInit() {}


  async viewCart(){
    
  }

}
