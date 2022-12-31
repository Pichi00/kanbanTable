import { NavigationContainer } from "@react-navigation/native";
import { registerRootComponent } from "expo";
import { StatusBar } from "expo-status-bar";
import { Text } from "react-native";
import { LandingScreen } from "./features/landing";
import { RootStackNavigator } from "./navigation/RootStack";
import { theme, ThemeProvider } from "./theme";

const App = () => {
  return (
    <ThemeProvider theme={theme} darkTheme={undefined} mode="light">
      <NavigationContainer>
        <RootStackNavigator />
      </NavigationContainer>
      <StatusBar style="auto" />
    </ThemeProvider>
  );
};

registerRootComponent(App);
