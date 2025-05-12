import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PictureService } from 'src/app/core/services/picture.service';
import { LoaderService } from 'src/app/shared/services/loader.service';

@Component({
  selector: 'app-picture-list',
  templateUrl: './picture-list.page.html',
  styleUrls: ['./picture-list.page.scss'],
  standalone: false
})
export class PictureListPage implements OnInit {
  pictures!: any[]
  searchTerm: string = '';
  filteredPictures: any[] = [];
  isGridView: boolean = false;

  constructor(private pictureSrv: PictureService,
    private router: Router,
    private loaderSrv: LoaderService
  ) { }

  async ngOnInit() {
    await this.loaderSrv.show("Loading pictures...");
    this.pictureSrv.getAll().subscribe((pictures: any[]) => {
      this.pictures = pictures;
      this.filteredPictures = pictures;
      this.loaderSrv.hide();
    });
  }

  filterPictures() {
    const term = this.searchTerm.trim().toLowerCase();
    this.filteredPictures = this.pictures.filter(p =>
      p.description?.toLowerCase().includes(term)
    );
  }

  async onSelect(id: any) {
    await this.router.navigate(['/picture-detail/' + id]);
  }

  deletePicture(id: any) {
    this.pictureSrv.delete(id).subscribe(() => {
      this.pictures = this.pictures.filter(picture => picture.id !== id);
    });
  }

  toggleView() {
    this.isGridView = !this.isGridView;
  }
}
