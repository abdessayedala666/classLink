import { Component, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './change-password.html',
  styleUrl: './change-password.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChangePassword {
  oldPassword = '';
  newPassword = '';
  confirmPassword = '';
  
  isLoading = false;
  errorMessage = '';
  successMessage = '';

  private readonly API_URL = 'http://localhost:8080/api/auth';

  constructor(
    private http: HttpClient,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private authService: AuthService
  ) {}

  get passwordsMatch(): boolean {
    return this.newPassword === this.confirmPassword;
  }

  get isFormValid(): boolean {
    return this.oldPassword.length >= 6 && 
           this.newPassword.length >= 6 && 
           this.confirmPassword.length > 0 && 
           this.passwordsMatch;
  }

  onSubmit(): void {
    if (!this.isFormValid) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';
    this.cdr.markForCheck();

    const payload = {
      oldPassword: this.oldPassword,
      newPassword: this.newPassword
    };

    this.http.post(`${this.API_URL}/change-password-first-login`, payload, { 
      observe: 'response',
      withCredentials: true 
    }).subscribe({
      next: (response) => {
        this.isLoading = false;
        if (response.status === 200) {
          this.successMessage = 'Password changed successfully! Logging out...';
          this.cdr.markForCheck();
          
          // Logout and redirect after a short delay
          setTimeout(() => {
            this.authService.logout().subscribe({
              next: () => {
                this.router.navigate(['/auth/login']);
              },
              error: () => {
                // Even if logout fails, redirect to login
                this.router.navigate(['/auth/login']);
              }
            });
          }, 1500);
        }
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = error.error?.message || 'Failed to change password. Please try again.';
        this.cdr.markForCheck();
      }
    });
  }
}
