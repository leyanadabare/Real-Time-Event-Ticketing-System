// import { Component } from '@angular/core';
// import { SystemService } from '../../services/system.service';
//
// @Component({
//   selector: 'app-control-panel',
//   template: `
//     <div>
//       <h2>Control Panel</h2>
//       <button (click)="startSystem()">Start System</button>
//       <button (click)="stopSystem()">Stop System</button>
//     </div>
//   `,
//   standalone: true
// })
// export class ControlPanelComponent {
//   constructor(private ticketService: SystemService) {}
//
//   startSystem() {
//     this.ticketService.updateSystemStatus(true);
//   }
//
//   stopSystem() {
//     this.ticketService.updateSystemStatus(false);
//   }
// }
// import { Component } from '@angular/core';
// import { SystemService } from '../../services/system.service';
//
// @Component({
//   selector: 'app-control-panel',
//   template: `
//     <div>
//       <h2>Control Panel</h2>
//       <button (click)="startSystem()">Start System</button>
//       <button (click)="stopSystem()">Stop System</button>
//     </div>
//   `,
//   standalone: true
// })
// export class ControlPanelComponent {
//   constructor(private systemService: SystemService) {}
//
//   startSystem() {
//     this.systemService.updateSystemStatus(true);
//   }
//
//   stopSystem() {
//     this.systemService.updateSystemStatus(false);
//   }
// }

// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-control-panel',
//   standalone: true,
//   templateUrl: './control-panel.component.html',
//   styleUrls: ['./control-panel.component.css']
// })
// export class ControlPanelComponent {
//   startSystem() {
//     console.log('System started');
//   }
//
//   stopSystem() {
//     console.log('System stopped');
//   }
// }
//
// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-control-panel',
//   standalone: true,
//   templateUrl: './control-panel.component.html',
//   styleUrls: ['./control-panel.component.css']
// })
// export class ControlPanelComponent {
//   startSystem() {
//     console.log('System started');
//   }
//
//   stopSystem() {
//     console.log('System stopped');
//   }
// }

//
// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-control-panel',
//   standalone: true,
//   templateUrl: './control-panel.component.html',
//   styleUrls: ['./control-panel.component.css'],
// })
// export class ControlPanelComponent {
//   startSystem() {
//     console.log('System started');
//   }
//
//   stopSystem() {
//     console.log('System stopped');
//   }
// }


//3rd
// import { Component, Inject, OnInit } from '@angular/core';
// import { SystemService } from '../../services/system.service'; // Updated import
// import { MatButtonModule } from '@angular/material/button';
// import { CommonModule } from '@angular/common';
//
// @Component({
//   selector: 'app-control-panel',
//   templateUrl: './control-panel.component.html',
//   styleUrls: ['./control-panel.component.css'],
//   imports: [
//     CommonModule,
//     MatButtonModule
//   ],
//   standalone: true
// })
// export class ControlPanelComponent implements OnInit {
//   actionType: string = 'run';
//
//   constructor(@Inject(SystemService) private systemService: SystemService) {}
//
//   ngOnInit() {}
//
//   onRunSimulation() {
//     const configData = {}; // Collect configuration data
//     this.systemService.runAndSaveConfiguration(configData).subscribe(
//       (response) => {
//         console.log('Simulation Started with Saved Configuration', response);
//       },
//       (error) => {
//         console.error('Error Starting Simulation:', error);
//       }
//     );
//   }
//
//   onRunWithoutSaving() {
//     const configData = {}; // Collect configuration data
//     this.systemService.runWithoutSaving(configData).subscribe(
//       (response) => {
//         console.log('Simulation Started Without Saving Configuration', response);
//       },
//       (error) => {
//         console.error('Error Starting Simulation:', error);
//       }
//     );
//   }
// }
import { Component, EventEmitter, Output } from '@angular/core';
import { Inject, OnInit } from '@angular/core';
import { SystemService } from '../../services/system.service';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css'],
  imports: [
    CommonModule,
    MatButtonModule
  ],
  standalone: true
})
export class ControlPanelComponent implements OnInit {
  @Output() systemStarted = new EventEmitter<void>();
  @Output() systemStopped = new EventEmitter<void>();
  actionType: string = 'run';

  constructor(@Inject(SystemService) private systemService: SystemService) {}

  ngOnInit() {}

  onRunSimulation() {
    const configData = {}; // Collect configuration data
    this.systemService.runAndSaveConfiguration(configData).subscribe(
      (response) => {
        console.log('Simulation Started with Saved Configuration', response);
      },
      (error) => {
        console.error('Error Starting Simulation:', error);
      }
    );
  }

  onRunWithoutSaving() {
    const configData = {}; // Collect configuration data
    this.systemService.runWithoutSaving(configData).subscribe(
      (response) => {
        console.log('Simulation Started Without Saving Configuration', response);
      },
      (error) => {
        console.error('Error Starting Simulation:', error);
      }
    );
  }
}

