import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-exams',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './exams.html',
  styleUrl: './exams.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Exams {}
