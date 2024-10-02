import { UserSessionDetails } from "@app/types/auth/AuthTypes";
import { createReducer, on } from "@ngrx/store";
import { loadUserSession, login, logout } from "./auth.actions";

export interface AuthState {
  isLoggedIn: boolean;
  sessionDetails: UserSessionDetails | null;
  token: string | null;
}

export const initialAuthState: AuthState = {
  isLoggedIn: false,
  sessionDetails: null,
  token: null,
};

export const authReducer = createReducer(
  initialAuthState,
  on(login, (state, { sessionDetails, token }) => ({
    ...state,
    isLoggedIn: true,
    sessionDetails,
    token,
  })),
  on(logout, () => ({
    isLoggedIn: false,
    sessionDetails: null,
    token: null,
  })),
  on(loadUserSession, (state, { sessionDetails }) => ({
    ...state,
    sessionDetails,
  }))
);
