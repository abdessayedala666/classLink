import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `<div class="dashboard"><h1>Student Dashboard</h1></div>`,
  styles: [`.dashboard { padding: 24px; }`],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class StudentDashboard {}
