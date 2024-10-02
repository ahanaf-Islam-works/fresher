import { createFeatureSelector, createSelector } from "@ngrx/store";
import { AuthState } from "./auth.reducer";

export const selectAuthState = createFeatureSelector<AuthState>("auth");

export const selectIsLoggedIn = createSelector(
  selectAuthState,
  (state: AuthState) => state.isLoggedIn
);

export const selectSessionDetails = createSelector(
  selectAuthState,
  (state: AuthState) => state.sessionDetails
);

export const selectToken = createSelector(
  selectAuthState,
  (state: AuthState) => state.token
);
