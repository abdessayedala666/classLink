import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-parent-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `<div class="dashboard"><h1>Parent Dashboard</h1></div>`,
  styles: [`.dashboard { padding: 24px; }`],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ParentDashboard {}
