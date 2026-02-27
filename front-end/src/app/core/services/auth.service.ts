import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';

export interface LoginPayload {
  email: string;
  password: string;
}

export interface User {
  email: string;
  name: string;
  role: string;
  firstLogin: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = 'http://localhost:8080/api/auth';

  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  public isAuthenticated$ = this.currentUser$.pipe(
    // Emits true if user exists, false otherwise
    map((user: any) => !!user)
  );

  constructor(private http: HttpClient) {}

  login(payload: LoginPayload): Observable<any> {
    return this.http.post(`${this.API_URL}/login`, payload, { 
      observe: 'response',
      withCredentials: true 
    }).pipe(
      tap((response: any) => {
        if (response.status === 200 && response.body) {
          this.currentUserSubject.next(response.body);
        }
      })
    );
  }

  logout(): Observable<any> {
    return this.http.post(`${this.API_URL}/logout`, {}, { 
      observe: 'response',
      withCredentials: true 
    }).pipe(
      tap(() => {
        this.currentUserSubject.next(null);
      })
    );
  }

  fetchCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/me`, { 
      withCredentials: true 
    }).pipe(
      tap((user: User) => {
        this.currentUserSubject.next(user);
      })
    );
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    return !!this.getCurrentUser();
  }

  isFirstLogin(): boolean {
    const user = this.getCurrentUser();
    return user?.firstLogin ?? false;
  }
}
