import { NavigatorScreenParams } from "@react-navigation/native";

export const RootStackRoutes = {
  Landing: "Landing",
  Register: "Register",
  SignIn: "Sign",
  App: "App",
} as const;

export const AppRoutes = {
  Table: "Table",
  TableList: "TableList",
  TableSettingsScreen: "TableSettingsScreen",
} as const;

export type RootStackParamList = {
  [RootStackRoutes.Landing]: undefined;
  [RootStackRoutes.Register]: undefined;
  [RootStackRoutes.SignIn]: undefined;
  [RootStackRoutes.App]: NavigatorScreenParams<AppParamList>;
};

export type AppParamList = {
  [AppRoutes.Table]: {
    tableId: number;
  };
  [AppRoutes.TableList]: undefined;
  [AppRoutes.TableSettingsScreen]: {
    tableId: number;
  };
};
