import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { of } from 'rxjs';
import { catchError, map, switchMap, take, tap } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Check if currentUser is already loaded
  return authService.currentUser$.pipe(
    take(1),
    switchMap(user => {
      if (user) {
        // user already loaded, return boolean
        return of(true);
      }
      return authService.fetchCurrentUser().pipe(
        take(1),
        map(fetchedUser => !!fetchedUser),
        catchError(err => {
          router.navigate(['/auth/login']);
          return of(false);
        })
      );
    }),
    tap(isAuth => {
      if (!isAuth) router.navigate(['/auth/login']);
    })
  );
};