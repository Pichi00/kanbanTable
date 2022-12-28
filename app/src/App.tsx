import { registerRootComponent } from "expo";
import { StatusBar } from "expo-status-bar";
import { Text } from "react-native";

const App = () => {
  return (
    <>
      <Text>Dzbanban</Text>
      <StatusBar style="auto" />
    </>
  );
};

registerRootComponent(App);
