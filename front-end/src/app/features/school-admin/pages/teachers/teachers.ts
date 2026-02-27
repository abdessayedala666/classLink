import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-teachers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './teachers.html',
  styleUrl: './teachers.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Teachers {}
