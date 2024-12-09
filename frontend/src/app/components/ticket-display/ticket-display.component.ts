import { Component, OnInit } from '@angular/core';
import { SystemService } from '../../services/system.service';

//import { TicketingSystemService } from '../../services/system.service';

@Component({
  selector: 'app-ticket-display',
  template: `
    <div>
      <h2>Ticket Availability</h2>
      <p>Tickets Available: {{ tickets }}</p>
    </div>
  `,
  standalone: true
})
export class TicketDisplayComponent implements OnInit {
  tickets = 0;

  constructor(private ticketService: SystemService) {}

  ngOnInit() {
    this.ticketService.tickets$.subscribe((count) => (this.tickets = count));
  }
}

