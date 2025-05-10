import { NgModule } from '@angular/core';

import { PictureListPageRoutingModule } from './picture-list-routing.module';

import { PictureListPage } from './picture-list.page';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  imports: [
    SharedModule,
    PictureListPageRoutingModule
  ],
  declarations: [PictureListPage]
})
export class PictureListPageModule {}
