import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'home/:q',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'stays',
    loadChildren: () => import('./pages/stays/stays.module').then( m => m.StaysPageModule)
  },
  {
    path: 'stays/:q',
    loadChildren: () => import('./pages/stays/stays.module').then( m => m.StaysPageModule)
  },
  {
    path: 'quick-tips',
    loadChildren: () => import('./pages/quick-tips/quick-tips.module').then( m => m.QuickTipsPageModule)
  },
  {
    path: 'quick-tips/:q',
    loadChildren: () => import('./pages/quick-tips/quick-tips.module').then( m => m.QuickTipsPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
