import { Component, OnInit } from '@angular/core';
import { CoordinatedOutfit } from '../entities/outfit';
import { ActivatedRoute } from '@angular/router';
import { OutfitService } from '../services/outfit.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-coordinated-outfits',
  templateUrl: './coordinated-outfits.page.html',
  styleUrls: ['./coordinated-outfits.page.scss'],
})
export class CoordinatedOutfitsPage implements OnInit {

  errorMessage = '';
  outfits: CoordinatedOutfit[];

  constructor(private outfitService: OutfitService, private activatedRoute: ActivatedRoute, private alertController: AlertController) { }

  ngOnInit() {
    this.outfitService.retrieveAllOutfits().subscribe(
      response => {
        console.log(response);
        this.outfits = response.outfits;
      },
      error => {
        this.errorMessage = error;
        this.presentAlert(this.errorMessage.substring(37));
      }
    );
  }


  async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'Error: ' + message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
