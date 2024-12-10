// import { Component } from '@angular/core';
// import { SystemService } from './services/system.service';
// // import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
// // import { ControlPanelComponent } from './components/control-panel/control-panel.component';
// // import { TicketDisplayComponent } from './components/ticket-display/ticket-display.component';
// // import { LogDisplayComponent } from './components/log-display/log-display.component'
// import { CommonModule } from '@angular/common';
// import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
//
// @Component({
//   selector: 'app-root',
//   standalone: true,
//   templateUrl: './app.component.html',
//   imports: [
//     CommonModule,
//     ConfigurationFormComponent,
//     ControlPanelComponent,
//     TicketDisplayComponent,
//     LogDisplayComponent,
//     HttpClientModule,
//   ],
// })
// export class AppComponent {
// }

// export class AppComponent {
//   constructor(private systemService: SystemService) {}
//
//   handleConfigSave(config: any) {
//     this.systemService.saveConfiguration(config).subscribe(
//       (response) => {
//         console.log('Configuration saved:', response);
//       },
//       (error) => {
//         console.error('Error saving configuration:', error);
//       }
//     );
//   }
// }
//
import { Component } from '@angular/core';
import { SystemService } from './services/system.service';
import { CommonModule } from '@angular/common';
//import { SystemService } from './services/system.service';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { TicketDisplayComponent } from './components/ticket-display/ticket-display.component';
import { LogDisplayComponent } from './components/log-display/log-display.component'
// import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';
// import { TicketDisplayComponent } from './ticket-display/ticket-display.component';
// import { ControlPanelComponent } from './control-panel/control-panel.component';
// import { LogDisplayComponent } from './log-display/log-display.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [
    CommonModule,
    ConfigurationFormComponent,
    TicketDisplayComponent,
    ControlPanelComponent,
    LogDisplayComponent
  ]
})
export class AppComponent {
  ticketCount: number = 0;
  logs: string[] = [];

  constructor(private sharedService: SystemService) {
    this.sharedService.ticketCount$.subscribe(count => this.ticketCount = count);
  }

  handleSettingsChanged(newSetting: string) {
    this.logs.push(`Configuration changed: ${newSetting}`);
  }

  handleSystemStarted() {
    this.logs.push('System started');
  }

  handleSystemStopped() {
    this.logs.push('System stopped');
  }
}
