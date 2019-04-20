import { Component, OnInit } from '@angular/core';
import { OutfitService } from '../services/outfit.service';
import { AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { CoordinatedOutfit } from '../entities/outfit';

@Component({
  selector: 'app-coordinated-outfit-details',
  templateUrl: './coordinated-outfit-details.page.html',
  styleUrls: ['./coordinated-outfit-details.page.scss'],
})

export class CoordinatedOutfitDetailsPage implements OnInit {

  errorMessage = '';
  outfitId: number;
  selectedOutfit = new CoordinatedOutfit();

  constructor(private outfitService: OutfitService, private activatedRoute: ActivatedRoute, private alertController: AlertController) { }

  ngOnInit() {
    this.outfitId = parseInt(this.activatedRoute.snapshot.paramMap.get('outfitId'));
    console.log("Outfit ID: " + this.outfitId);
    this.outfitService.retrieveOutfit(this.outfitId).subscribe(
      response => {
        this.selectedOutfit = response.outfit;
      },
      error => {
        this.errorMessage = error;
        this.presentAlert(error.substring(37));
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
