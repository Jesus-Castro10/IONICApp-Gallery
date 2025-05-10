import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoaderService } from './services/loader.service';
import { ToastService } from './services/toast.service';

const EXPORTS = [CommonModule, IonicModule, ReactiveFormsModule, FormsModule];

@NgModule({
  declarations: [],
  imports: [
    EXPORTS
  ],
  exports: [
    ...EXPORTS
  ],
  providers: [LoaderService,ToastService],
})
export class SharedModule { }
