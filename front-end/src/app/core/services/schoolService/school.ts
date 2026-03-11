import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { tap } from 'rxjs/internal/operators/tap';
export interface School {
  id: number;
  name: string;
  address: string;
}
@Injectable({
  providedIn: 'root',
})
export class SchoolService {
  private apiUrl = 'http://localhost:8080/api/schools';
  private schoolSubject = new BehaviorSubject<School |null>(null) ;
  school$: Observable<School | null> = this.schoolSubject.asObservable();  
constructor(private http : HttpClient) {
  }
  fetchSchool(){
    return this.http.get<School>(`${this.apiUrl}` , {withCredentials : true}).pipe(
      tap (school => this.schoolSubject.next(school))
    )
    }
    setSchool(school : School){
      this.schoolSubject.next(school) ; 
  }
    clearSchool() {
    this.schoolSubject.next(null);
  }

}

  

  

