import { Component, OnInit } from '@angular/core';
import { SystemService } from '../../services/system.service';

@Component({
  selector: 'app-log-display',
  template: `
    <div>
      <h2>System Logs</h2>
      <ul>
        <li *ngFor="let log of logs">{{ log }}</li>
      </ul>
    </div>
  `,
  standalone: true
})
export class LogDisplayComponent implements OnInit {
  logs: string[] = [];

  constructor(private ticketService: SystemService) {}

  ngOnInit() {
    // Replace with actual log subscription logic if available
    // This is just a placeholder.
    this.logs = ['System initialized.', 'Ticket pool created.'];
  }
}

