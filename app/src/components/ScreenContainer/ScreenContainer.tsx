import {
  Platform,
  SafeAreaView,
  ScrollView,
  ViewStyle,
  View,
  TouchableWithoutFeedback,
  Keyboard,
} from "react-native";
import { useTheme } from "../../theme";

type Props = {
  children: React.ReactNode;
  style?: ViewStyle;
  hasKeyobardDismisser?: boolean;
};

export const ScreenContainer = ({
  hasKeyobardDismisser = true,
  ...props
}: Props) => {
  return hasKeyobardDismisser ? (
    <TouchableWithoutFeedback
      style={{
        flex: 1,
        alignSelf: "stretch",
      }}
      onPress={() => {
        Keyboard.dismiss();
      }}
    >
      <ScreenContainerInner {...props} />
    </TouchableWithoutFeedback>
  ) : (
    <ScreenContainerInner {...props} />
  );
};

const ScreenContainerInner = ({
  children,
  style,
}: Pick<Props, "children" | "style">) => {
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
      {Platform.OS === "android" ? (
        children
      ) : (
        <View
          style={[
            {
              flex: 1,
              alignSelf: "stretch",
              paddingHorizontal: theme.spacing.$5,
              paddingBottom: theme.spacing.$5,
            },
            style,
          ]}
        >
          {children}
        </View>
      )}
    </SafeAreaView>
  );
};
