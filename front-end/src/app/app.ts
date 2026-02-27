import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './core/services/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  protected readonly title = signal('front-end');
  constructor(private authService : AuthService){
    this.authService.fetchCurrentUser().subscribe({
      next : (user) => {
        console.log('App: fetchCurrentUser success:', user);
      },
      error : (err) => {
        console.error('App: fetchCurrentUser error:', err);
        
      }
    })
  }

}
