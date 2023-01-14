import { RegisterSchemaType } from "../features/auth/components/RegisterForm";
import { apiClient } from "./axios";

type SignInBody = {
  email: string;
  password: string;
};
type SignInResponse = string;

export const signInEmailAndPassword = async (body: SignInBody) => {
  const response = await apiClient.post<SignInResponse>("/auth/token", body);

  if (response.status !== 200) {
    throw new Error("Invalid credentials");
  }

  return response.data;
};

type RegisterBody = RegisterSchemaType;
type RegisterResponse = unknown;

export const register = async (body: RegisterBody) => {
  const response = await apiClient.post<RegisterResponse>(
    "/auth/register",
    body,
  );

  if (response.status !== 200) {
    throw new Error("Something went wrong, please try again");
  }

  return response.data;
};
