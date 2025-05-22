import { Component } from '@angular/core';
import { ShortestPathService } from '../../../service/shortestPath/shortest-path.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shortest-path',
  imports: [CommonModule],
  templateUrl: './shortest-path.component.html',
  styleUrl: './shortest-path.component.css'
})
export class ShortestPathComponent {

  constructor(private shortestPathService: ShortestPathService) { }

  ngOnInit(): void {
    
  }

}
