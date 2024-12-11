//
// //3rd
// import { Component, OnInit } from '@angular/core';
// import { SystemService } from '../../services/system.service'; // Updated import
// import { CommonModule } from '@angular/common';
//
// @Component({
//   selector: 'app-log-display',
//   templateUrl: './log-display.component.html',
//   styleUrls: ['./log-display.component.css'],
//   imports: [CommonModule],
//   standalone: true
// })
// export class LogDisplayComponent implements OnInit {
//   logs: string[] = [];
//
//   constructor(private systemService: SystemService) {}
//
//   ngOnInit() {
//     this.fetchLogs();
//   }
//
//   fetchLogs() {
//     this.systemService.getLogs().subscribe(
//       (response: string[]) => {
//         this.logs = response;
//       },
//       (error) => {
//         console.error('Error fetching logs:', error);
//       }
//     );
//   }
// }
//
import { Component, Input, OnInit } from '@angular/core';
import { SystemService } from '../../services/system.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-log-display',
  templateUrl: './log-display.component.html',
  styleUrls: ['./log-display.component.css'],
  imports: [CommonModule],
  standalone: true
})
export class LogDisplayComponent implements OnInit {
  @Input() logs: string[] = [];
  //logs: string[] = [];

  constructor(private systemService: SystemService) {}

  ngOnInit() {
    this.fetchLogs();
  }

  fetchLogs() {
    this.systemService.getLogs().subscribe(
      (response: string[]) => {
        this.logs = response;
      },
      (error) => {
        console.error('Error fetching logs:', error);
      }
    );
  }
}
