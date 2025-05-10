import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PictureService } from 'src/app/core/services/picture.service';

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
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.pictureId = params.get('id') || '';
      console.log(this.pictureId);
      this.pictureSrv.getById(this.pictureId).subscribe((picture: any) => {
        this.picture = picture;
        console.log(this.picture);
      });
    });
  }

}
