import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-sizeguide',
  templateUrl: './sizeguide.page.html',
  styleUrls: ['./sizeguide.page.scss'],
})
export class SizeguidePage implements OnInit {

  constructor(private modalController: ModalController) { }

  ngOnInit() {
  }

  closeModal() {
    this.modalController.dismiss();
  }
}
