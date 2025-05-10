import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PictureListPage } from './picture-list.page';

describe('PictureListPage', () => {
  let component: PictureListPage;
  let fixture: ComponentFixture<PictureListPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PictureListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
