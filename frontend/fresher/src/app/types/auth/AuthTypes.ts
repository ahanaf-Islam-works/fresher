export interface SignUpFormType {
  userName: string;
  email: string;
  password: string;
  rollNumber: number;
  userType: 'EXPLORER' | 'PROVIDER';
}

export interface UserSessionDetails {
  userName: string;
  userType: 'EXPLORER' | 'PROVIDER';
  rollNumber: number;
}
