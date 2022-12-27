import { makeTheme, DripsyProvider, H1 } from "dripsy";
import { registerRootComponent } from "expo";
import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

const theme = makeTheme({});

const App = () => {
  return (
    <DripsyProvider theme={theme}>
      <View style={styles.container}>
        <Text>Dzbanban</Text>
        <StatusBar style="auto" />
      </View>
    </DripsyProvider>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});

registerRootComponent(App);
