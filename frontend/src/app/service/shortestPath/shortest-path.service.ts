import { Injectable } from '@angular/core';
import { backendBase } from '../../util/config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShortestPathDataDto } from '../../dto/shortestPath/ShortestPathDataDto';
import { ShortestPathDto } from '../../dto/shortestPath/ShortestPathDto';
import { CustomGraph } from '../../dto/shortestPath/CustomGraph';
import { CustomEdge } from '../../dto/shortestPath/CustomEdge';

@Injectable({
  providedIn: 'root'
})
export class ShortestPathService {
  shortestPathUri = backendBase + '/shortestPath';

  constructor(private http: HttpClient) { }

    getRandomGraph(): Observable<CustomGraph> {
      return this.http.get<CustomGraph>(this.shortestPathUri);
    }

    getShortestPath(shortestPathDto: ShortestPathDto): Observable<CustomEdge[]> {
      return this.http.post<CustomEdge[]>(this.shortestPathUri, shortestPathDto);
    }

    getShortestPathData(): Observable<ShortestPathDataDto> {
      return this.http.get<ShortestPathDataDto>(this.shortestPathUri + '/data');
    }
}
