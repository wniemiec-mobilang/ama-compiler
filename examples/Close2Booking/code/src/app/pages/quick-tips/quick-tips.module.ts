import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { QuickTipsPage } from './quick-tips.page';
import { QuickTipsPageRoutingModule } from './quick-tips-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    QuickTipsPageRoutingModule
  ],
  declarations: [QuickTipsPage]
})
export class QuickTipsPageModule {}
