import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoaderService } from './services/loader.service';
import { ToastService } from './services/toast.service';
import { HeaderComponent } from './components/header/header.component';

const EXPORTS = [CommonModule, IonicModule, ReactiveFormsModule, FormsModule];
const COMPONENTS = [HeaderComponent];
@NgModule({
  declarations: [...COMPONENTS,],
  imports: [
    ...EXPORTS,
  ],
  exports: [
    ...EXPORTS,
    ...COMPONENTS,
  ],
  providers: [LoaderService,ToastService],
})
export class SharedModule { }
