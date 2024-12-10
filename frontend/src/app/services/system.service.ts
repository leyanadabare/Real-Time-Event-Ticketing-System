// //updated service.ts -2
// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
//
// @Injectable({
//   providedIn: 'root', // Keep this as is
// })
// export class SystemService {
//
//   private apiUrl = 'http://localhost:8080'; // Update with your API URL
//
//   constructor(private http: HttpClient) {}
//
//   // Methods for ControlPanelComponent
//   runAndSaveConfiguration(configData: any): Observable<any> {
//     return this.http.post(`${this.apiUrl}/runWithSave`, configData);
//   }
//
//   runWithoutSaving(configData: any): Observable<any> {
//     return this.http.post(`${this.apiUrl}/runWithoutSave`, configData);
//   }
//
//   // Methods for LogDisplayComponent
//   getLogs(): Observable<string[]> {
//     return this.http.get<string[]>(`${this.apiUrl}/logs`);
//   }
//
//   // Methods for TicketDisplayComponent
//   getTickets(): Observable<string[]> {
//     return this.http.get<string[]>(`${this.apiUrl}/tickets`);
//   }
// }
//
//
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Keep this as is
})
export class SystemService {
  private ticketCountSubject = new BehaviorSubject<number>(0);
  ticketCount$ = this.ticketCountSubject.asObservable();

  updateTicketCount(count: number) {
    this.ticketCountSubject.next(count);
  }


  private apiUrl = 'http://localhost:8080'; // Update with your API URL

  constructor(private http: HttpClient) {}

  // Methods for ControlPanelComponent
  runAndSaveConfiguration(configData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/runWithSave`, configData);
  }

  runWithoutSaving(configData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/runWithoutSave`, configData);
  }

  // Methods for LogDisplayComponent
  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/logs`);
  }

  // Methods for TicketDisplayComponent
  getTickets(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/tickets`);
  }
}
