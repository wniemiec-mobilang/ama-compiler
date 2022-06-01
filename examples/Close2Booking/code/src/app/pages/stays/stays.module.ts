import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { StaysPage } from './stays.page';
import { StaysPageRoutingModule } from './stays-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    StaysPageRoutingModule
  ],
  declarations: [StaysPage]
})
export class StaysPageModule {}
