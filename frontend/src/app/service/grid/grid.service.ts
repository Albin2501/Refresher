import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { backendBase } from '../../util/config';
import { GridDto } from '../../dto/grid/GridDto';
import { GridSelectionDto } from '../../dto/grid/GridSelectionDto';
import { Observable } from 'rxjs';
import { GridDataDto } from '../../dto/grid/GridDataDto';

@Injectable({
  providedIn: 'root'
})
export class GridService {
  gridUri = backendBase + '/grid';

  constructor(private http: HttpClient) { }

  getGridData(): Observable<GridDataDto> {
    return this.http.get<GridDataDto>(this.gridUri + '/data');
  }

  getGrid(id: number): Observable<GridDto> {
    return this.http.get<GridDto>(this.gridUri + '/' + id);
  }

  putGrid(id: number, gridSelectionDto: GridSelectionDto): Observable<GridDto> {
    return this.http.put<GridDto>(this.gridUri + '/' + id, gridSelectionDto);
  }

  clearGrid(id: number): Observable<GridDto> {
    return this.http.put<GridDto>(this.gridUri + '/clear/' + id, null);
  }
}
