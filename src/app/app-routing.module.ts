import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'picture-list',
    loadChildren: () => import('./pages/picture-list/picture-list.module').then( m => m.PictureListPageModule)
  },
  {
    path: 'picture-detail/:id',
    loadChildren: () => import('./pages/picture-detail/picture-detail.module').then( m => m.PictureDetailPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
