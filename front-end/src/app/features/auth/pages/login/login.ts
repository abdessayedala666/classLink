import { Component, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Login {
  loginForm: FormGroup;
  isLoading = false;
  errorMessage = '';
  showPassword = false ;

  ngOnInit(): void {
    this.authService.isAuthenticated$.subscribe(isAuth => {
      console.log('Login: isAuthenticated$ =', isAuth);
    });
    this.authService.currentUser$.subscribe(user => {
      console.log('Login: currentUser$ =', user);
  })}

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }
  togglePasswordVisibility() : void{
    this.showPassword = !this.showPassword ;
    
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.cdr.markForCheck();

    const payload = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password
    };

    this.authService.login(payload).subscribe({
      next: (response) => {
        this.isLoading = false;
        console.log('Login response:', response.status, response.body);
        
        if (response.status === 200) {
          const { role, firstLogin } = response.body;
          
          // Check if first login - redirect to change password
          if (firstLogin) {
            this.router.navigate(['/auth/change-password']);
            return;
          }
          
          // Route based on role
          this.routeByRole(role);
        }
        this.cdr.markForCheck();
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.error?.message || `Error: ${error.status}`;
        console.log('Login error:', error.status, error.error);
        this.cdr.markForCheck();
      }
    });
  }

  private routeByRole(role: string): void {
    switch (role) {
      case 'SCHOOL_ADMIN':
        this.router.navigate(['/school-admin/dashboard']);
        break;
      case 'TEACHER':
        this.router.navigate(['/teacher/dashboard']);
        break;
      case 'STUDENT':
        this.router.navigate(['/student/dashboard']);
        break;
      case 'PARENT':
        this.router.navigate(['/parent/dashboard']);
        break;
      default:
        this.router.navigate(['/']);
        break;
    }
  }
}
