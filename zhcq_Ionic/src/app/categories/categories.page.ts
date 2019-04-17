import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { Category } from '../entities/category';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.page.html',
  styleUrls: ['./categories.page.scss'],
})
export class CategoriesPage implements OnInit {
  public searchControl: FormControl;
  errorMessage: string;
  categories: Category[];

  constructor(private categoryService: CategoryService, private activatedRoute: ActivatedRoute, private alertController: AlertController ) {
    this.searchControl = new FormControl();
  }

  ngOnInit() {

    this.categoryService.retrieveAllCategories().subscribe(
      response => {
        this.categories = response.categories;
      },
      error => {
        this.errorMessage = error;
      }
    );
  }

}
