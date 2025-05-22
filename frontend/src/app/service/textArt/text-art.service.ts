import { Injectable } from '@angular/core';
import { backendBase } from '../../util/config';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { TextArtDto } from '../../dto/textArt/TextArtDto';
import { ImageDto } from '../../dto/textArt/ImageDto';

@Injectable({
  providedIn: 'root'
})
export class TextArtService {
  textArtUri = backendBase + '/textArt';

  constructor(private http: HttpClient) { }

  getTextArt(): Observable<TextArtDto[]> {
    return this.http.get<TextArtDto[]>(this.textArtUri).pipe(
      map(textArtDtoArray => {
        return textArtDtoArray.map(
          textArtDto => {
          // manually convert string to Date
          textArtDto.art = textArtDto.art;
          textArtDto.id = textArtDto.id;
          textArtDto.name = textArtDto.name;
          textArtDto.time = new Date(textArtDto.time);
          return textArtDto;
      })}));
  }

  postTextArt(imageDto: ImageDto): Observable<TextArtDto> {
    // file can't be send in a JSON, formData helps build key/value pairs
    // for form fields, required for sending forms with files
    const formData = new FormData();
    formData.append('image', imageDto.image);
    formData.append('width', imageDto.width.toString());
    formData.append('height', imageDto.height.toString());
    return this.http.post<TextArtDto>(this.textArtUri, formData);
  }

  deleteAllTextArt(): Observable<boolean> {
    return this.http.delete<boolean>(this.textArtUri);
  }

  deleteTextArt(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.textArtUri + '/' + id);
  }
}
