import { Component } from '@angular/core';
import { SystemService } from '../../services/system.service';

@Component({
  selector: 'app-control-panel',
  template: `
    <div>
      <h2>Control Panel</h2>
      <button (click)="startSystem()">Start System</button>
      <button (click)="stopSystem()">Stop System</button>
    </div>
  `,
  standalone: true
})
export class ControlPanelComponent {
  constructor(private ticketService: SystemService) {}

  startSystem() {
    this.ticketService.updateSystemStatus(true);
  }

  stopSystem() {
    this.ticketService.updateSystemStatus(false);
  }
}
