import { Text, View } from "react-native";
import { useTheme } from "../../../theme";

export const LandingScreen = () => {
  const { theme } = useTheme();

  return (
    <View
      style={{
        backgroundColor: theme.colors.background,
        flex: 1,
      }}
    ></View>
  );
};
