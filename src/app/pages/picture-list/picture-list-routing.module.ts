import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PictureListPage } from './picture-list.page';

const routes: Routes = [
  {
    path: '',
    component: PictureListPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PictureListPageRoutingModule {}
