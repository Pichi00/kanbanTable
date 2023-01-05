import { StyleProp, TouchableOpacity, View, ViewStyle } from "react-native";
import { RectButton } from "react-native-gesture-handler";
import { useTheme } from "../../theme";

export type ButtonProps = {
  children: React.ReactNode;
  onPress: () => void;
  style?: StyleProp<ViewStyle>;
};

export const Button = ({ children, onPress, style }: ButtonProps) => {
  const { theme } = useTheme();

  return (
    <TouchableOpacity
      onPress={onPress}
      activeOpacity={0.7}
      style={{
        alignSelf: "stretch",
      }}
    >
      <View
        style={[
          {
            backgroundColor: theme.colors.surfaceDark,
            justifyContent: "center",
            alignItems: "center",
            paddingVertical: theme.spacing.$4,
            borderRadius: theme.radii.$3,
            borderWidth: 2,
            borderColor: theme.colors.text,
          },
          style,
        ]}
      >
        {children}
      </View>
    </TouchableOpacity>
  );
};
