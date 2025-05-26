import { Component, HostListener } from '@angular/core';
import { ShortestPathService } from '../../../service/shortestPath/shortest-path.service';
import { CommonModule } from '@angular/common';
import { CustomGraph } from '../../../dto/shortestPath/CustomGraph';
import { ShortestPathSelectionDto } from '../../../dto/shortestPath/ShortestPathSelectionDto';
import { CustomEdge } from '../../../dto/shortestPath/CustomEdge';
import { ShortestPathDataDto } from '../../../dto/shortestPath/ShortestPathDataDto';

@Component({
  selector: 'app-shortest-path',
  imports: [CommonModule],
  templateUrl: './shortest-path.component.html',
  styleUrl: './shortest-path.component.css'
})
export class ShortestPathComponent {
  customGraph: CustomGraph = {} as CustomGraph;
  shortestPathSelectionDto: ShortestPathSelectionDto = {} as ShortestPathSelectionDto;
  shortestPathDataDto: ShortestPathDataDto = {} as ShortestPathDataDto;
  shortestPath: CustomEdge[] = [];

  constructor(private shortestPathService: ShortestPathService) { }

  ngOnInit(): void {
    this.get();
  }

  get(): void {
    this.shortestPathService.getRandomGraph().subscribe({
        next: customGraph => {
          this.customGraph = customGraph;
          this.resetNodes();
        }
      }
    );
  }

  post(): void {
    this.shortestPathService.getShortestPath(this.shortestPathSelectionDto).subscribe({
      next: shortestPath => {
          this.shortestPath = shortestPath;
          this.resetNodes();
        }
      }
    );
  }

  getData(): void {
    this.shortestPathService.getShortestPathData().subscribe({
      next: shortestPathDataDto => {
          this.shortestPathDataDto = shortestPathDataDto;
        }
      }
    );
  }

  // TODO: figure out why this works
  @HostListener('contextmenu', ['$event'])
  onRightClick(event: Event) {
    event.preventDefault();
  }

  setNode(node: string, event: MouseEvent): void {
    if (event.button === 0) this.shortestPathSelectionDto.startNode = node;
    if (event.button === 2) this.shortestPathSelectionDto.endNode = node;
  }

  resetNodes(): void {
    this.shortestPathSelectionDto = {} as ShortestPathSelectionDto;
  }
}
