export interface User {
  id: number;
  username: string;
  email: string;
  role: string;
  firstName?: string;
  lastName?: string;
}

export interface AuthRequest {
  usernameOrEmail: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  message: string;
  type: string;
  id: number;
  username: string;
  email: string;
  role: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  firstName?: string;
  lastName?: string;
}
