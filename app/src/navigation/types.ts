export const RootStackRoutes = {
  Landing: "Landing",
  Register: "Register",
  SignIn: "Sign",
} as const;

export type RootStackParamList = {
  [RootStackRoutes.Landing]: undefined;
  [RootStackRoutes.Register]: undefined;
  [RootStackRoutes.SignIn]: undefined;
};
