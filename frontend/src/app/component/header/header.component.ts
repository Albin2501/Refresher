import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-header',
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  menuItem: string[] = [
    'REFRESHER',
    'GRID'
  ];
  menuItemLink: string[] = [
    '',
    '/grid'
  ];
  activeItem: boolean[] = [];

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        const currentUrl = event.urlAfterRedirects;
        var homepageActive = false;

        for (let i = 0; i < this.menuItemLink.length; i++) {
          this.activeItem[i] = currentUrl === this.menuItemLink[i];
          homepageActive = homepageActive || this.activeItem[i];
        }
        this.activeItem[0] = !homepageActive;
      });
  }

  activateMenuItem(id: number): void {
    for (let i = 0; i < this.activeItem.length; i++) {
      this.activeItem[i] = i === id;
    }
  }
}
