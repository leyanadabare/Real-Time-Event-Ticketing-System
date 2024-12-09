import { Component } from '@angular/core';
import { SystemService } from './services/system.service';
import { CommonModule } from '@angular/common'; // Add this for standalone components
import { TicketDisplayComponent } from './components/ticket-display/ticket-display.component';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
import { LogDisplayComponent } from './components/log-display/log-display.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    CommonModule, // Required for standalone components
    TicketDisplayComponent,
    ControlPanelComponent,
    ConfigurationFormComponent,
    LogDisplayComponent,
  ],
  standalone: true,
})
export class AppComponent {
  title = 'frontend'; // Add this line
  constructor(private ticketService: SystemService) {}

  updateTickets(count: number) {
    this.ticketService.updateTickets(count);
  }
}
