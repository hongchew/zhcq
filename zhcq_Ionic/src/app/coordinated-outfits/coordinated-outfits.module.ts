import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { CoordinatedOutfitsPage } from './coordinated-outfits.page';

const routes: Routes = [
  {
    path: '',
    component: CoordinatedOutfitsPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [CoordinatedOutfitsPage]
})
export class CoordinatedOutfitsPageModule {}
