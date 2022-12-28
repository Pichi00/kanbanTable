import { Platform, SafeAreaView, ScrollView, ViewStyle } from "react-native";
import { useTheme } from "../../theme";

type Props = {
  children: React.ReactNode;
  style?: ViewStyle;
};

export const ScreenContainer = ({ children, style = {} }: Props) => {
  const { theme } = useTheme();

  return (
    <SafeAreaView
      style={[
        {
          flex: 1,
          alignSelf: "stretch",
          paddingTop: Platform.OS === "android" ? 50 : 0,
          paddingHorizontal: theme.spacing.$5,
          paddingBottom: theme.spacing.$5,
        },
        style,
      ]}
    >
      {children}
    </SafeAreaView>
  );
};
