import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SystemService {
  private tickets: number = 0;
  tickets$: any;

  updateTickets(count: number) {
    this.tickets = count;
  }
  getTickets(): number {
    return this.tickets;
  }
  constructor() { }

  updateSystemStatus(b: boolean) {

  }
}
