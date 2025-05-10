import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PictureDetailPage } from './picture-detail.page';

describe('PictureDetailPage', () => {
  let component: PictureDetailPage;
  let fixture: ComponentFixture<PictureDetailPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PictureDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
