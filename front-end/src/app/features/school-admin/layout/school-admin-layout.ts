import { Component, ChangeDetectionStrategy } from '@angular/core';
import { OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '../../../shared/components/navbar/navbar';
import { Sidebar } from '../components/sidebar/sidebar';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-school-admin-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet, Navbar, Sidebar],
  templateUrl: './school-admin-layout.html',
  styleUrl: './school-admin-layout.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SchoolAdminLayout implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isAuthenticated$.subscribe(isAuth => {
      console.log('SchoolAdminLayout: isAuthenticated$ =', isAuth);
    });
  }
}
