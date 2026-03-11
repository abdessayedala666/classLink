import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { School, SchoolService } from '../../../../core/services/schoolService/school';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})


export class Sidebar  implements OnInit{
  menuItems = [
    { path: '/school-admin/grades', label: 'Grades', icon: '📊' },
    { path: '/school-admin/classrooms', label: 'Classrooms', icon: '🏫' },
    { path: '/school-admin/teachers', label: 'Teachers', icon: '👨‍🏫' },
    { path: '/school-admin/students', label: 'Students', icon: '👨‍🎓' },
    { path: '/school-admin/exams', label: 'Exams', icon: '📝' },
    { path: '/school-admin/analytics', label: 'Analytics', icon: '📈' },
  ];
  school$: Observable<School | null>;

  constructor(private schoolService : SchoolService) {
    this.school$ = this.schoolService.school$;
  }

  ngOnInit() {
    this.schoolService.fetchSchool().subscribe() ;
    console.log(this.school$) ;
  }


}
