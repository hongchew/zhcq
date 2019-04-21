import { Component, OnInit } from '@angular/core';
import { PromotionService } from '../services/promotion.service';
import { Promotion } from '../entities/promotion';
import { AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-promotion-page',
  templateUrl: './promotion-page.page.html',
  styleUrls: ['./promotion-page.page.scss'],
})
export class PromotionPagePage implements OnInit {

  errorMessage = '';
  promotion= new Promotion();
  promotionId: number;;

  constructor(private promotionService: PromotionService, private activatedRoute: ActivatedRoute, private alertController: AlertController) { }

  ngOnInit() {
    this.promotionId = parseInt(this.activatedRoute.snapshot.paramMap.get('promotionId'));
    console.log("promo ID: " + this.promotionId);
    this.promotionService.retrievePromotionByPromotionId(this.promotionId).subscribe(
      response => {
        this.promotion = response.promotion;
        console.log('promo retrieved = ' + this.promotion.promotionName);
      },
      error => {
        this.errorMessage = error;
        this.presentAlert(this.errorMessage.substring(37));
      }
    );
   }

   async presentAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'ERROR: ' + message,
      buttons: ['OK']
    });
    await alert.present();
  }

}
