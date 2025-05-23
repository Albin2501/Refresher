import { Injectable } from '@angular/core';
import { backendBase } from '../../util/config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShortestPathDataDto } from '../../dto/shortestPath/ShortestPathDataDto';
import { CustomGraph } from '../../dto/shortestPath/CustomGraph';
import { CustomEdge } from '../../dto/shortestPath/CustomEdge';
import { ShortestPathSelectionDto } from '../../dto/shortestPath/ShortestPathSelectionDto';

@Injectable({
  providedIn: 'root'
})
export class ShortestPathService {
  shortestPathUri = backendBase + '/shortestPath';

  constructor(private http: HttpClient) { }

    getRandomGraph(): Observable<CustomGraph> {
      return this.http.get<CustomGraph>(this.shortestPathUri);
    }

    getShortestPath(shortestPathSelectionDto: ShortestPathSelectionDto): Observable<CustomEdge[]> {
      return this.http.post<CustomEdge[]>(this.shortestPathUri, shortestPathSelectionDto);
    }

    getShortestPathData(): Observable<ShortestPathDataDto> {
      return this.http.get<ShortestPathDataDto>(this.shortestPathUri + '/data');
    }
}
