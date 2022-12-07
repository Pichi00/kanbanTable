import { makeTheme, DripsyProvider, H1 } from "dripsy";
import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";

const theme = makeTheme({});

const App = () => {
  return (
    <DripsyProvider theme={theme}>
      <View style={styles.container}>
        <H1>Open up App.js to start working on your app!</H1>
        <StatusBar style="auto" />
      </View>
    </DripsyProvider>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
