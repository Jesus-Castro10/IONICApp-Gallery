import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: false,
})
export class HeaderComponent  implements OnInit {
  @Input() title: string = '';
  @Input() showBackButton: boolean = false;
  @Input() showHomeButton: boolean = false;

  constructor(private router: Router) { }

  ngOnInit() {}

  goHome(){
    this.router.navigate(['/home']);
  }

  goGallery(){
    this.router.navigate(['/picture-list']);
  }
}
