import { LoaderService } from '../../shared/services/loader.service';
import { Component, OnInit } from '@angular/core';
import { PictureService } from '../../core/services/picture.service';
import { CameraService } from '../../core/services/camera.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BucketService } from '../../core/services/bucket.service';
import { ToastService } from '../../shared/services/toast.service';
import { StorageService } from 'src/app/core/services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: false,
})
export class HomePage{
  previewImage: string | null = null;
  imageBlob: Blob | null = null;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private pictureSrv: PictureService,
    private cameraSrv: CameraService,
    private bucketSrv: BucketService,
    private loaderSrv: LoaderService,
    private toastSrv: ToastService,
  ) {
    this.form = this.fb.group({
      description: ['', Validators.required],
      image: [null, Validators.required]
    });
  }

  async pickImage() {
    try {
      this.imageBlob = await this.cameraSrv.pickPicture();
      this.previewImage = URL.createObjectURL(this.imageBlob);
      this.form.patchValue({ image: this.imageBlob });
    } catch (err) {
      console.error('Error capturando imagen', err);
    }
  }

  async onSave() {
    if (!this.form.valid || !this.imageBlob) return;
    try {
      this.loaderSrv.show('Guardando...');
      const fileName = `image-${Date.now()}.jpg`;
      const url = await this.bucketSrv.uploadImage(this.imageBlob, fileName);
      const picture = {
        description: this.form.value.description,
        url
      };

      this.pictureSrv.save(picture).then(() => {
        this.loaderSrv.hide();
        this.toastSrv.presentToast('Imagen guardada correctamente', 'success');
        this.form.reset();
        this.previewImage = null;
        this.imageBlob = null;
      }).catch((error) => {
        console.error('Error al guardar la imagen en Firestore:', error);
        this.loaderSrv.hide();
        this.toastSrv.presentToast('Error al guardar la imagen en Firestore', 'danger');
      });
    } catch (err) {
      console.error('Error al guardar los datos:', err);
    }
  }

}
