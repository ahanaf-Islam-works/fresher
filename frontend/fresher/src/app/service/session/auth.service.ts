import { Injectable, inject } from "@angular/core";
import { loadUserSession, login, logout } from "@app/store/auth.actions";
import { AuthState } from "@app/store/auth.reducer";
import { SignUpFormType, UserSessionDetails } from "@app/types/auth/AuthTypes";
import { Store } from "@ngrx/store";
import { RestService } from "../api/rest.service";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private apiService = inject(RestService);
  private store = inject(Store<AuthState>);

  public signUpService(signUpFormData: SignUpFormType) {
    return this.apiService.post("/auth/sign-up", signUpFormData);
  }

  public signInService(signInFormData: { email: string; password: string }) {
    return this.apiService.post("/auth/sign-in", signInFormData);
  }

  public saveSessionDetails(sessionDetails: UserSessionDetails, token: string) {
    this.store.dispatch(login({ sessionDetails, token }));
    localStorage.setItem("userSessionDetails", JSON.stringify(sessionDetails));
    sessionStorage.setItem("token", token);
  }

  public logOut() {
    this.store.dispatch(logout());
    localStorage.removeItem("userSessionDetails");
    sessionStorage.removeItem("token");
  }

  public getSessionDetails() {
    const sessionDetails = JSON.parse(
      localStorage.getItem("userSessionDetails") || ""
    );
    if (sessionDetails) {
      this.store.dispatch(loadUserSession({ sessionDetails }));
    }
    return sessionDetails;
  }
}
