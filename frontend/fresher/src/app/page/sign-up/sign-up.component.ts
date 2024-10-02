import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatOption, MatSelect } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '@app/service/session/auth.service';
import { catchError } from 'rxjs';
@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [ReactiveFormsModule, MatSelect, MatOption],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
})
export class SignUpComponent {
  authService = inject(AuthService);
  snackBar = inject(MatSnackBar);
  router = inject(Router);

  signUpForm = new FormGroup({
    userName: new FormControl('', { nonNullable: true }),
    email: new FormControl('', { nonNullable: true }),
    password: new FormControl('', { nonNullable: true }),
    rollNumber: new FormControl<number>(0, { nonNullable: true }),
    userType: new FormControl<'EXPLORER' | 'PROVIDER'>('EXPLORER', {
      nonNullable: true,
    }),
  });

  public resString: string = '';

  onSubmit() {
    if (this.signUpForm.valid) {
      const formValue = this.signUpForm.getRawValue();
      formValue.rollNumber = Number(formValue.rollNumber);

      this.authService
        .signUpService(formValue)
        .pipe(
          catchError((error) => {
            this.snackBar.open(error.error, 'Close', {
              duration: 4000,
              panelClass: 'snack-bar-error',
            });
            throw error;
          }),
        )
        .subscribe(() => {
          this.router.navigate(['/sign-in']);
        });
    } else {
      this.snackBar.open('Please fill all the fields', 'Close', {
        duration: 2000,
        panelClass: 'snack-bar-error',
      });
    }
  }
}
