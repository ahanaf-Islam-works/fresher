import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '@app/service/session/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, NgFor, NgIf],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent implements OnInit {
  authService = inject(AuthService);
  router = inject(Router);

  public isLoggedIn = false;

  ngOnInit(): void {
    console.log(this.authService.loggedIn());
    this.isLoggedIn = this.authService.loggedIn();
    console.log(this.isLoggedIn);
  }

  public pagesLinks = [
    {
      name: 'home',
      link: '/',
    },
    {
      name: 'about',
      link: '/about',
    },
    {
      name: 'blogs',
      link: '/blogs',
    },
    {
      name: 'dashboard',
      link: '/dashboard',
    },
  ];

  public sessionLinks = [
    {
      name: 'Sign In',
      link: '/sign-in',
    },
    {
      name: 'Sign Up',
      link: '/sign-up',
    },
  ];

  public onLogout() {
    this.authService.logOut();
    this.router.navigate(['/']);
  }
}
