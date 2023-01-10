import { PortalProvider } from "@gorhom/portal";
import { NavigationContainer } from "@react-navigation/native";
import { registerRootComponent } from "expo";
import { StatusBar } from "expo-status-bar";
import { Text, View } from "react-native";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import { LandingScreen } from "./features/landing";
import { RootStackNavigator } from "./navigation/RootStack";
import { theme, ThemeProvider } from "./theme";
import { SafeAreaProvider } from "react-native-safe-area-context";
import {
  useFonts,
  Montserrat_400Regular,
  Montserrat_700Bold,
  Montserrat_900Black,
} from "@expo-google-fonts/montserrat";
import { Asul_400Regular, Asul_700Bold } from "@expo-google-fonts/asul";
import * as SplashScreen from "expo-splash-screen";
import { useCallback } from "react";

SplashScreen.preventAutoHideAsync();

const App = () => {
  const [fontsLoaded] = useFonts({
    Montserrat_400Regular,
    Montserrat_700Bold,
    Montserrat_900Black,
    Asul_400Regular,
    Asul_700Bold,
  });

  const onLayoutRootView = useCallback(async () => {
    if (fontsLoaded) {
      await SplashScreen.hideAsync();
    }
  }, [fontsLoaded]);

  if (!fontsLoaded) {
    return null;
  }

  return (
    <View style={{ flex: 1 }} onLayout={onLayoutRootView}>
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
    </View>
  );
};

registerRootComponent(App);
