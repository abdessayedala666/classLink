import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-classrooms',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './classrooms.html',
  styleUrl: './classrooms.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Classrooms {}
