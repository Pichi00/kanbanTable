import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { RegisterScreen } from "../features/auth/components/RegisterScreen";
import { SignInScreen } from "../features/auth/components/SignInScreen";
import { LandingScreen } from "../features/landing";
import { TableScreen } from "../features/table";
import { useAuth } from "../hooks/useAuth";
import { useTheme } from "../theme";
import { AppDrawerNavigator } from "./AppDrawer";
import { RootStackParamList, RootStackRoutes } from "./types";

const RootStack = createNativeStackNavigator<RootStackParamList>();

export const RootStackNavigator = () => {
  const { isAuthenticated } = useAuth();
  const { theme } = useTheme();

  return (
    <RootStack.Navigator
      screenOptions={{
        headerStyle: {
          backgroundColor: theme.colors.background,
        },
        headerTitleStyle: {
          color: theme.colors.text,
        },
        headerBackTitleVisible: false,
        headerTintColor: theme.colors.text,
        headerShadowVisible: false,
        // headerShown: false,
      }}
      initialRouteName={
        isAuthenticated ? RootStackRoutes.Landing : RootStackRoutes.App
      }
    >
      {isAuthenticated ? (
        <>
          <RootStack.Screen
            name={RootStackRoutes.App}
            component={AppDrawerNavigator}
            options={{
              headerShown: false,
            }}
          />
        </>
      ) : (
        <>
          <RootStack.Screen
            name={RootStackRoutes.Landing}
            component={LandingScreen}
            options={{
              title: "Dzbanban",
              headerShown: false,
            }}
          />
          <RootStack.Screen
            name={RootStackRoutes.Register}
            component={RegisterScreen}
            options={{
              title: "Register",
              presentation: "modal",
            }}
          />
          <RootStack.Screen
            name={RootStackRoutes.SignIn}
            component={SignInScreen}
            options={{
              title: "Sign In",
              presentation: "modal",
            }}
          />
        </>
      )}
    </RootStack.Navigator>
  );
};
