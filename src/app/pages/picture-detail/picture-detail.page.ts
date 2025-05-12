import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PictureService } from 'src/app/core/services/picture.service';
import { LoaderService } from 'src/app/shared/services/loader.service';

@Component({
  selector: 'app-picture-detail',
  templateUrl: './picture-detail.page.html',
  styleUrls: ['./picture-detail.page.scss'],
  standalone: false
})
export class PictureDetailPage implements OnInit {

  picture: any;
  pictureId: string = '';
  constructor(private pictureSrv: PictureService,
    private route: ActivatedRoute,
    private loaderSrv: LoaderService
  ) { }

  ngOnInit() {
    this.loaderSrv.show("Loading picture...");
    this.pictureId = this.route.snapshot.paramMap.get('id') || '';
    console.log(this.pictureId);
    
    this.pictureSrv.getById(this.pictureId).subscribe((picture: any) => {
      this.picture = picture;
      console.log(this.picture);
      this.loaderSrv.hide();
    });
    
  }

}
