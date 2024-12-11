//
// //3rd
// import { Component, OnInit } from '@angular/core';
// import { SystemService } from '../../services/system.service'; // Updated import
// import { CommonModule } from '@angular/common';
//
// @Component({
//   selector: 'app-ticket-display',
//   templateUrl: './ticket-display.component.html',
//   styleUrls: ['./ticket-display.component.css'],
//   imports: [CommonModule],
//   standalone: true
// })
// export class TicketDisplayComponent implements OnInit {
//   tickets: string[] = [];
//
//   constructor(private systemService: SystemService) {}
//
//   ngOnInit() {
//     this.fetchTickets();
//   }
//
//   fetchTickets() {
//     this.systemService.getTickets().subscribe(
//       (response: string[]) => {
//         this.tickets = response;
//       },
//       (error) => {
//         console.error('Error fetching tickets:', error);
//       }
//     );
//   }
// }
import { Component, Input, OnInit } from '@angular/core';
import { SystemService } from '../../services/system.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ticket-display',
  templateUrl: './ticket-display.component.html',
  styleUrls: ['./ticket-display.component.css'],
  imports: [CommonModule],
  standalone: true
})
export class TicketDisplayComponent implements OnInit {
  @Input() ticketCount: number = 0;
  tickets: string[] = [];

  constructor(private systemService: SystemService) {}

  ngOnInit() {
    this.fetchTickets();
  }

  fetchTickets() {
    this.systemService.getTickets().subscribe(
      (response: string[]) => {
        this.tickets = response;
      },
      (error) => {
        console.error('Error fetching tickets:', error);
      }
    );
  }
}
