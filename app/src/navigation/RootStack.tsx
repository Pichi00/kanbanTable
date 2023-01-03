import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { RegisterScreen } from "../features/auth/components/RegisterScreen";
import { LandingScreen } from "../features/landing";
import { useTheme } from "../theme";
import { RootStackParamList, RootStackRoutes } from "./types";

const RootStack = createNativeStackNavigator<RootStackParamList>();

export const RootStackNavigator = () => {
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
        // headerShown: false,
      }}
      initialRouteName={RootStackRoutes.Landing}
    >
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
    </RootStack.Navigator>
  );
};
