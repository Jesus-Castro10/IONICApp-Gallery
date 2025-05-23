import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PictureDetailPage } from './picture-detail.page';

const routes: Routes = [
  {
    path: '',
    component: PictureDetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PictureDetailPageRoutingModule {}
