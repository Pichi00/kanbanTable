import { PortalProvider } from "@gorhom/portal";
import { NavigationContainer } from "@react-navigation/native";
import { registerRootComponent } from "expo";
import { StatusBar } from "expo-status-bar";
import { Text } from "react-native";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import { LandingScreen } from "./features/landing";
import { RootStackNavigator } from "./navigation/RootStack";
import { theme, ThemeProvider } from "./theme";
import { SafeAreaProvider } from "react-native-safe-area-context";

const App = () => {
  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <ThemeProvider theme={theme} darkTheme={undefined} mode="light">
        <PortalProvider>
          <SafeAreaProvider>
            <NavigationContainer>
              <RootStackNavigator />
            </NavigationContainer>
          </SafeAreaProvider>
        </PortalProvider>
        <StatusBar style="auto" />
      </ThemeProvider>
    </GestureHandlerRootView>
  );
};

registerRootComponent(App);
