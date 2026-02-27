import { Routes } from '@angular/router';

// Guards
import { firstLoginGuard } from './guards/first-login/first-login-guard';

// Layouts
import { AuthLayout } from './features/auth/layout/auth-layout';
import { MainLayout } from './features/main/layout/main-layout';
import { SchoolAdminLayout } from './features/school-admin/layout/school-admin-layout';
import { TeacherLayout } from './features/teacher/layout/teacher-layout';
import { StudentLayout } from './features/student/layout/student-layout';
import { ParentLayout } from './features/parent/layout/parent-layout';

// Auth Pages
import { Login } from './features/auth/pages/login/login';
import { Register } from './features/auth/pages/register/register';
import { ChangePassword } from './features/auth/pages/change-password/change-password';

// Main Pages
import { Home } from './features/main/pages/home/home';

// School Admin Pages
import { SchoolAdminDashboard } from './features/school-admin/pages/dashboard/school-admin-dashboard';
import { Grades } from './features/school-admin/pages/grades/grades';
import { Classrooms } from './features/school-admin/pages/classrooms/classrooms';
import { Teachers } from './features/school-admin/pages/teachers/teachers';
import { Students } from './features/school-admin/pages/students/students';
import { Exams } from './features/school-admin/pages/exams/exams';
import { Analytics } from './features/school-admin/pages/analytics/analytics';

// Teacher Pages
import { TeacherDashboard } from './features/teacher/pages/dashboard/teacher-dashboard';

// Student Pages
import { StudentDashboard } from './features/student/pages/dashboard/student-dashboard';

// Parent Pages
import { ParentDashboard } from './features/parent/pages/dashboard/parent-dashboard';
import { authGuard } from './guards/auth/auth-guard';

export const routes: Routes = [
  // ===== SCHOOL ADMIN =====
  {
    path: 'school-admin',
    component: SchoolAdminLayout,
    canActivate : [authGuard] ,
 
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: SchoolAdminDashboard },
      { path: 'grades', component: Grades },
      { path: 'grades/:gradeId/classrooms', component: Classrooms },
      { path: 'classrooms', component: Classrooms },
      { path: 'teachers', component: Teachers },
      { path: 'students', component: Students },
      { path: 'exams', component: Exams },
      { path: 'analytics', component: Analytics },
    ],
  },

  // ===== TEACHER =====
  {
    path: 'teacher',
    component: TeacherLayout,
    canActivate: [firstLoginGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: TeacherDashboard },
    ],
  },

  // ===== STUDENT =====
  {
    path: 'student',
    component: StudentLayout,
    canActivate: [firstLoginGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: StudentDashboard },
    ],
  },

  // ===== PARENT =====
  {
    path: 'parent',
    component: ParentLayout,
    canActivate: [firstLoginGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: ParentDashboard },
    ],
  },

  // ===== AUTH =====
  {
    path: 'auth',
    component: AuthLayout,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: Login },
      { path: 'register', component: Register },
      { path: 'change-password', component: ChangePassword },
    ],
  },

  // ===== MAIN / PUBLIC =====
  {
    path: '',
    component: MainLayout,
    children: [{ path: '', component: Home }],
  },
];
