import { Component } from '@angular/core';
import { TextArtService } from '../../service/textArt/text-art.service';
import { TextArtDto } from '../../dto/textArt/TextArtDto';
import { ImageDto } from '../../dto/textArt/ImageDto';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-text-art',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './text-art.component.html',
  styleUrl: './text-art.component.css'
})
export class TextArtComponent {
  formGroup: FormGroup;
  textArtDto: TextArtDto[] = [];
  selectedArt: number = 0;
  clipBoard: boolean = false;
  selectedImage: boolean = false;
  deleteConfirmation: boolean = false;

  // TODO: when selected same image -> problem?

  constructor(private textArtService: TextArtService, private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      width: [256, Validators.required],
      height: [128, Validators.required],
      image: [null, Validators.required]
    });
  }
  
  ngOnInit(): void {
    this.getTextArt();
  }

  getTextArt(): void {
    // postTextArt(), deleteAllTextArt() and deleteTextArt() are not calling getTextArt()
    // because of asynchronization with textArtDto and selectedArt
    // get-requests are being done within those functions
    this.textArtService.getTextArt().subscribe({
      next: textArtDto => {
        this.textArtDto = textArtDto;
      }
    });
  }

  postTextArt(): void {
    this.textArtService.postTextArt(this.formGroup.value as ImageDto).subscribe({
      next: () => {
        this.textArtService.getTextArt().subscribe({
          next: textArtDto => {
            this.textArtDto = textArtDto;
            this.formGroup.patchValue({image: null});
            if (this.textArtDto.length === 1) this.selectedArt = 0;
            else this.selectedArt++;
          }
        });
      }
    });
  }

  deleteAllTextArt(): void {
    this.textArtService.deleteAllTextArt().subscribe({
      next: () => {
        this.textArtService.getTextArt().subscribe({
          next: textArtDto => {
            this.textArtDto = textArtDto;
            this.selectedArt = 0;
            this.deleteConfirmation = false;
          }
        });
      }
    });
  }

  deleteTextArt(id: number, index: number): void {
    this.textArtService.deleteTextArt(id).subscribe({
      next: () => {
        this.textArtService.getTextArt().subscribe({
          next: textArtDto => {
            this.textArtDto = textArtDto;
            if (this.textArtDto.length === 0 || this.selectedArt === index && index === 0)
            this.selectedArt = 0;
            else if (this.selectedArt >= index)
            this.selectedArt--;
          }
        });
      }
    });
  }

  onImageChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0 && input.files[0].size < 15000000) // 15MB maximum
    this.formGroup.patchValue({image: input.files[0]});
  }

  copyToClipboard(art: string) {
    navigator.clipboard.writeText(art);
  }

  update(s: string, n: number): void {
    const control = this.formGroup.get(s);
    if (control) control.setValue(control.value + n);
  }
}
