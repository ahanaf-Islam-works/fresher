import { UserSessionDetails } from "@app/types/auth/AuthTypes";
import { createAction, props } from "@ngrx/store";

export const login = createAction(
  "[Auth] Login",
  props<{ sessionDetails: UserSessionDetails; token: string }>()
);

export const logout = createAction("[Auth] Logout");

export const loadUserSession = createAction(
  "[Auth] Load User Session",
  props<{ sessionDetails: UserSessionDetails | null }>()
);
