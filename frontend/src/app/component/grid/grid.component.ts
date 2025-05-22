import { Component } from '@angular/core';
import { GridService } from '../../service/grid/grid.service';
import { GridDataDto } from '../../dto/grid/GridDataDto';
import { GridDto } from '../../dto/grid/GridDto';
import { GridSelectionDto } from '../../dto/grid/GridSelectionDto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-grid',
  imports: [CommonModule],
  templateUrl: './grid.component.html',
  styleUrl: './grid.component.css'
})
export class GridComponent {

  gridDataDto: GridDataDto = {} as GridDataDto;
  gridDto: GridDto = {} as GridDto;
  gridColor: string[][] = [];
  gridSelectionDto: GridSelectionDto = {} as GridSelectionDto;
  pos1: number[] = [];
  pos2: number[] = [];
  activeCells: boolean[][] = [];
  correctMouseLogic: boolean = false;
  valMap: Map<number, string> = new Map();
  helpText: boolean = false;

  constructor(private gridService: GridService) { }

  ngOnInit(): void {
    this.getGridData();
  }

  getGridData(): void {
    this.gridService.getGridData().subscribe({
      next: gridDataDto => {
        this.gridDataDto = gridDataDto;
        this.resetColor();
        this.resetActiveCells();
        this.getGrid(this.gridDataDto.ids[0]);
      }
    });
  }

  getGrid(id: number): void {
    this.gridService.getGrid(id).subscribe({
      next: gridDto => {
        this.resetColor();
        this.gridDto = gridDto;
        this.colorGrid();
      }
    });
  }

  putGrid(id: number, gridSelection: GridSelectionDto): void {
    this.gridService.putGrid(id, gridSelection).subscribe({
      next: gridDto => {
        this.gridDto = gridDto;
        this.colorGrid();
        this.resetMouse();
      },
      complete: () => {
        this.resetActiveCells();
      }
    });
  }

  clearGrid(id: number): void {
    this.gridService.clearGrid(id).subscribe({
      next: gridDto => {
        this.resetColor();
        this.gridDto = gridDto;
      }
    });
  }

  mouseDown(pos1x: number, pos1y: number, event: MouseEvent): void {
    if (event.button === 1 || event.button === 2) return; // only left mouse-button
    this.pos1[0] = pos1x;
    this.pos1[1] = pos1y;
    this.correctMouseLogic = true;
  }

  mouseUp(pos2x: number, pos2y: number, event: MouseEvent): void {
    if (event.button === 1 || event.button === 2) return; // only left mouse-button
    this.pos2[0] = pos2x;
    this.pos2[1] = pos2y;
    this.putGrid(this.gridDto.id, {pos1: this.pos1, pos2: this.pos2} as GridSelectionDto);
  }

  mouseHover(posx: number, posy: number, event: MouseEvent): void {
    if (event.button === 1 || event.button === 2) return; // only left mouse-button
    if (!this.correctMouseLogic) {
      this.pos1[0] = posx;
      this.pos1[1] = posy;
    }
    const startx = Math.min(this.pos1[0], posx);
    const starty = Math.min(this.pos1[1], posy);
    const endx = Math.max(this.pos1[0], posx);
    const endy = Math.max(this.pos1[1], posy);

    for (let i = 0; i < this.gridDto.grid.length; i++) {
      for (let j = 0; j < this.gridDto.grid[i].length; j++) {
        if (i >= startx && i <= endx && j >= starty && j <= endy)
        this.activeCells[i][j] = true;
        else this.activeCells[i][j] = false;
      }
    }
  }

  colorGrid(): void {
    var val;
    
    for (let i = 0; i < this.gridDto.grid.length; i++) {
      for (let j = 0; j < this.gridDto.grid[i].length; j++) {
        val = this.gridDto.grid[i][j];
  
        if (val !== 0) {
          if (!this.valMap.has(val))
          this.valMap.set(val, this.randomColor());

          this.gridColor[i][j] = this.valMap.get(val) ?? 'inherit';
        }
      }
    }
  }

  resetColor(): void {
    this.gridColor = new Array(this.gridDataDto.n);
    for (let i = 0; i < this.gridColor.length; i++) {
      this.gridColor[i] = new Array(this.gridDataDto.m);
    }
  }

  resetMouse(): void {
    this.pos1 = [];
    this.pos2 = [];
    this.correctMouseLogic = false;
  }

  resetActiveCells(): void {
    this.activeCells = new Array(this.gridDataDto.n);
    for (let i = 0; i < this.activeCells.length; i++) {
      this.activeCells[i] = new Array(this.gridDataDto.m);
    }
  }

  randomColor(): string {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return `rgb(${r}, ${g}, ${b})`;
  }

  trackByIndex(index: number): number {
    // Optimizes grid to only update divs in the DOM if changes occurred
    return index;
  }
}
