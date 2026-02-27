import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-school-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `<div class="dashboard"><h1>School Admin Dashboard</h1></div>`,
  styles: [`.dashboard { padding: 24px; }`],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SchoolAdminDashboard {}
