import { NgFor, NgIf } from "@angular/common";
import { Component, inject } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { AuthService } from "@app/service/session/auth.service";
import { AuthState } from "@app/store/auth.reducer";
import { Store } from "@ngrx/store";
import { Observable } from "rxjs";

@Component({
  selector: "app-navbar",
  standalone: true,
  imports: [RouterLink, NgFor, NgIf],
  templateUrl: "./navbar.component.html",
  styleUrl: "./navbar.component.scss",
})
export class NavbarComponent {
  authService = inject(AuthService);
  router = inject(Router);

  isLoggedIn$: Observable<boolean>;

  constructor(private store: Store<AuthState>) {
    this.isLoggedIn$ = this.store.select((state) => state.isLoggedIn);
  }

  public pagesLinks = [
    {
      name: "home",
      link: "/",
    },
    {
      name: "about",
      link: "/about",
    },
    {
      name: "blogs",
      link: "/blogs",
    },
    {
      name: "dashboard",
      link: "/dashboard",
    },
  ];

  public sessionLinks = [
    {
      name: "Sign In",
      link: "/sign-in",
    },
    {
      name: "Sign Up",
      link: "/sign-up",
    },
  ];

  public onLogout() {
    this.authService.logOut();
  }
}
