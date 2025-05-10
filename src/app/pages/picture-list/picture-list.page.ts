import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PictureService } from 'src/app/core/services/picture.service';

@Component({
  selector: 'app-picture-list',
  templateUrl: './picture-list.page.html',
  styleUrls: ['./picture-list.page.scss'],
  standalone: false
})
export class PictureListPage implements OnInit {
  pictures!: any[]

  constructor(private pictureSrv: PictureService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.pictureSrv.getAll().subscribe((pictures: any[]) => {
      this.pictures = pictures;
      console.log(this.pictures);
    });
  }

  onSelect(picture: any) {
    console.log(picture);
    this.router.navigate(['picture-detail', picture.id]);
  }
}
