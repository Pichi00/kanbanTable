import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { LandingScreen } from "../features/landing";

export const RootStackRoutes = {
  Landing: "Landing",
} as const;

export type RootStackParamList = {
  [RootStackRoutes.Landing]: undefined;
};

const RootStack = createNativeStackNavigator<RootStackParamList>();

export const RootStackNavigator = () => {
  return (
    <RootStack.Navigator
      screenOptions={{
        headerShown: false,
      }}
      initialRouteName={RootStackRoutes.Landing}
    >
      <RootStack.Screen
        name={RootStackRoutes.Landing}
        component={LandingScreen}
      />
    </RootStack.Navigator>
  );
};
