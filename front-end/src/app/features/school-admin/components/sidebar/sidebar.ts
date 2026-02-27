import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Sidebar {
  menuItems = [
    { path: '/school-admin/grades', label: 'Grades', icon: '📊' },
    { path: '/school-admin/classrooms', label: 'Classrooms', icon: '🏫' },
    { path: '/school-admin/teachers', label: 'Teachers', icon: '👨‍🏫' },
    { path: '/school-admin/students', label: 'Students', icon: '👨‍🎓' },
    { path: '/school-admin/exams', label: 'Exams', icon: '📝' },
    { path: '/school-admin/analytics', label: 'Analytics', icon: '📈' },
  ];
}
