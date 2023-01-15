import { Asul_400Regular, Asul_700Bold } from "@expo-google-fonts/asul";
import {
  Montserrat_400Regular,
  Montserrat_700Bold,
  Montserrat_900Black,
  useFonts,
} from "@expo-google-fonts/montserrat";
import { PortalProvider } from "@gorhom/portal";
import { NavigationContainer } from "@react-navigation/native";
import {
  focusManager,
  QueryClient,
  QueryClientProvider,
} from "@tanstack/react-query";
import { registerRootComponent } from "expo";
import * as SplashScreen from "expo-splash-screen";
import { StatusBar } from "expo-status-bar";
import { useCallback } from "react";
import { AppStateStatus, Platform, View } from "react-native";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import { SafeAreaProvider } from "react-native-safe-area-context";
import { AuthProvider } from "./features/auth/context/AuthContext";
import { useAppState } from "./hooks/useAppState";
import { useOnlineManager } from "./hooks/useOnlineManager";
import { RootStackNavigator } from "./navigation/RootStack";
import { theme, ThemeProvider } from "./theme";

const onAppStateChange = (status: AppStateStatus) => {
  if (Platform.OS !== "web") {
    focusManager.setFocused(status === "active");
  }
};

SplashScreen.preventAutoHideAsync();

const App = () => {
  useOnlineManager();

  useAppState(onAppStateChange);

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
      <AuthProvider>
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
      </AuthProvider>
    </View>
  );
};

registerRootComponent(App);
