import { Component } from '@angular/core';
import { backendBase } from '../../util/config';

@Component({
  selector: 'app-homepage',
  imports: [],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {
  swaggerUri = backendBase + '/swagger-ui/index.html';
  albinUri = 'https://github.com/Albin2501';
}
