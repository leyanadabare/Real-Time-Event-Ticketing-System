import { Component, EventEmitter, Output } from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-configuration-form',
  template: `
    <div>
      <h2>Configuration Settings</h2>
      <label>
        Initial Tickets:
        <input type="number" [(ngModel)]="initialTickets"/>
      </label>
      <button (click)="saveConfig()">Save Configuration</button>
    </div>
  `,
  imports: [
    FormsModule
  ],
  standalone: true
})
export class ConfigurationFormComponent {
  @Output() configSaved = new EventEmitter<number>();
  initialTickets = 0;

  saveConfig() {
    this.configSaved.emit(this.initialTickets);
  }
}

