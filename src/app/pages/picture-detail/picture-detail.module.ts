import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PictureDetailPageRoutingModule } from './picture-detail-routing.module';

import { PictureDetailPage } from './picture-detail.page';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  imports: [
    SharedModule,
    PictureDetailPageRoutingModule
  ],
  declarations: [PictureDetailPage]
})
export class PictureDetailPageModule {}
