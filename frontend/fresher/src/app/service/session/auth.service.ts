import { Injectable, inject, signal } from '@angular/core';
import { SignUpFormType, UserSessionDetails } from '@app/types/auth/AuthTypes';
import { RestService } from '../api/rest.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiService = inject(RestService);

  private sessionDetails: UserSessionDetails | null = null;

  public loggedIn = signal<boolean>(false);

  public signUpService(signUpFormData: SignUpFormType) {
    return this.apiService.post('/auth/sign-up', signUpFormData);
  }

  public signInService(signInFormData: { email: string; password: string }) {
    return this.apiService.post('/auth/sign-in', signInFormData);
  }

  public saveSessionDetails(sessionDetails: UserSessionDetails, token: string) {
    console.log(sessionDetails);
    this.loggedIn.set(true);
    console.log(this.loggedIn());
    localStorage.setItem('userSessionDetails', JSON.stringify(sessionDetails));
    sessionStorage.setItem('token', token);
  }

  public getSessionDetails() {
    this.sessionDetails = JSON.parse(
      localStorage.getItem('sessionDetails') || '',
    );
    if (this.sessionDetails && this.loggedIn()) {
      return this.sessionDetails;
    } else {
      return null;
    }
  }

  public getToken() {
    return JSON.parse(sessionStorage.getItem('token') || '') as string;
  }

  public logOut() {
    console.log(this.loggedIn());
    this.loggedIn.set(false);
    localStorage.removeItem('userSessionDetails');
    sessionStorage.removeItem('token');
  }
}
