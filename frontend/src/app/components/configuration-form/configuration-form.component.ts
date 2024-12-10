//
// //next trial
// import { Component, Inject, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
// import { SystemService } from '../../services/system.service'; // Updated import
// import { MatFormFieldModule } from '@angular/material/form-field';
// import { MatInputModule } from '@angular/material/input';
// import { MatButtonModule } from '@angular/material/button';
// import { CommonModule } from '@angular/common';
//
// @Component({
//   selector: 'app-configuration-form',
//   templateUrl: './configuration-form.component.html',
//   styleUrls: ['./configuration-form.component.css'],
//   imports: [
//     CommonModule,
//     ReactiveFormsModule,
//     MatFormFieldModule,
//     MatInputModule,
//     MatButtonModule
//   ],
//   standalone: true
// })
// export class ConfigurationFormComponent implements OnInit {
//   configForm!: FormGroup;
//   actionType: string = '';
//
//   constructor(private fb: FormBuilder, @Inject(SystemService) private systemService: SystemService) {} // Updated injection
//
//   ngOnInit() {
//     this.configForm = this.fb.group({
//       ticketPoolCapacity: [10, [Validators.required, Validators.min(1)]],
//       numberOfVendors: [1, [Validators.required, Validators.min(1)]],
//       numberOfCustomers: [1, [Validators.required, Validators.min(1)]],
//       retrievalRate: [1, [Validators.required, Validators.min(1)]],
//       releaseRate: [1, [Validators.required, Validators.min(1)]],
//       totalTickets: [50, [Validators.required, Validators.min(1)]],
//     });
//   }
//
//   onSubmit(actionType: string) {
//     if (this.configForm.valid) {
//       const configData = this.configForm.value;
//
//       if (actionType === 'run') {
//         this.systemService.runAndSaveConfiguration(configData).subscribe(  // Updated service call
//           (response) => {
//             console.log('Simulation Started with Saved Configuration', response);
//           },
//           (error) => {
//             console.error('Error Starting Simulation:', error);
//           }
//         );
//       } else if (actionType === 'runWithoutSaving') {
//         this.systemService.runWithoutSaving(configData).subscribe( // Updated service call
//           (response) => {
//             console.log('Simulation Started Without Saving Configuration', response);
//           },
//           (error) => {
//             console.error('Error Starting Simulation:', error);
//           }
//         );
//       }
//     } else {
//       console.error('Form is invalid');
//     }
//   }
// }
//
//
//
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { SystemService } from '../../services/system.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-configuration-form',
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  standalone: true
})
export class ConfigurationFormComponent implements OnInit {
  @Output() settingsChanged = new EventEmitter<string>();
  configForm!: FormGroup;
  actionType: string = '';

  constructor(private fb: FormBuilder, @Inject(SystemService) private systemService: SystemService) {} // Updated injection

  ngOnInit() {
    this.configForm = this.fb.group({
      ticketPoolCapacity: [10, [Validators.required, Validators.min(1)]],
      numberOfVendors: [1, [Validators.required, Validators.min(1)]],
      numberOfCustomers: [1, [Validators.required, Validators.min(1)]],
      retrievalRate: [1, [Validators.required, Validators.min(1)]],
      releaseRate: [1, [Validators.required, Validators.min(1)]],
      totalTickets: [50, [Validators.required, Validators.min(1)]],
    });
  }

  onSubmit(actionType: string) {
    this.settingsChanged.emit(actionType);
    if (this.configForm.valid) {
      const configData = this.configForm.value;

      if (actionType === 'run') {
        this.systemService.runAndSaveConfiguration(configData).subscribe(  // Updated service call
          (response) => {
            console.log('Simulation Started with Saved Configuration', response);
          },
          (error) => {
            console.error('Error Starting Simulation:', error);
          }
        );
      } else if (actionType === 'runWithoutSaving') {
        this.systemService.runWithoutSaving(configData).subscribe( // Updated service call
          (response) => {
            console.log('Simulation Started Without Saving Configuration', response);
          },
          (error) => {
            console.error('Error Starting Simulation:', error);
          }
        );
      }
    } else {
      console.error('Form is invalid');
    }
  }
}
