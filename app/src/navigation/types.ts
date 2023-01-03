export const RootStackRoutes = {
  Landing: "Landing",
  Register: "Register",
} as const;

export type RootStackParamList = {
  [RootStackRoutes.Landing]: undefined;
  [RootStackRoutes.Register]: undefined;
};
