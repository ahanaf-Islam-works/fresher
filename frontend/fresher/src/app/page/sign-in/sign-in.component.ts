import { Component, inject } from "@angular/core";
import { FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { AuthService } from "@app/service/session/auth.service";
import { catchError } from "rxjs";

@Component({
  selector: "app-sign-in",
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: "./sign-in.component.html",
  styleUrl: "./sign-in.component.scss",
})
export class SignInComponent {
  snackBar = inject(MatSnackBar);
  authService = inject(AuthService);
  router = inject(Router);

  signInForm = new FormGroup({
    email: new FormControl("", { nonNullable: true }),
    password: new FormControl("", { nonNullable: true }),
  });

  resString = "";

  onSubmit() {
    if (!this.signInForm.valid) {
      this.snackBar.open("Please fill all the fields", "Close", {
        duration: 2000,
        panelClass: "snack-bar-error",
      });
    } else {
      this.authService
        .signInService(this.signInForm.getRawValue())
        .pipe(
          catchError((error) => {
            this.snackBar.open(JSON.stringify(error.message), "Close", {
              duration: 4000,
              panelClass: "snack-bar-error",
            });
            throw error;
          })
        )
        .subscribe((response: any) => {
          this.authService.loggedIn.set(true);
          this.authService.saveSessionDetails(
            {
              userName: response.userName,
              userType: response.userType,
              rollNumber: response.rollNumber,
            },
            response.token
          );

          this.router.navigate(["/dashboard"]);
        });
    }
  }
}
