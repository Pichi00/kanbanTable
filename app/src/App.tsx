import { registerRootComponent } from "expo";
import { StatusBar } from "expo-status-bar";
import { Text } from "react-native";
import { LandingScreen } from "./features/landing";
import { theme, ThemeProvider } from "./theme";

const App = () => {
  return (
    <ThemeProvider
      theme={theme}
      darkTheme={{
        colors: {
          background: "black",
        },
      }}
      mode="light"
    >
      <LandingScreen />
      <StatusBar style="auto" />
    </ThemeProvider>
  );
};

registerRootComponent(App);
