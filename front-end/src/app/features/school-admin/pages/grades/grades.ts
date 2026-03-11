import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-grades',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './grades.html',
  styleUrl: './grades.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class Grades {

}
