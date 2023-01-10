import {
  StyleProp,
  StyleSheet,
  TouchableOpacity,
  View,
  ViewStyle,
} from "react-native";
import { RectButton } from "react-native-gesture-handler";
import { useTheme } from "../../theme";

export type ButtonProps = {
  children: React.ReactNode;
  onPress: () => void;
  style?: StyleProp<ViewStyle>;
  noShadow?: boolean;
};

export const Button = ({
  children,
  onPress,
  style,
  noShadow = false,
}: ButtonProps) => {
  const { theme } = useTheme();
  const translateY = theme.spacing.$2;

  return (
    <View
      style={{
        alignSelf: "stretch",
        marginBottom: translateY,
      }}
    >
      {!noShadow && (
        <View
          style={{
            ...StyleSheet.absoluteFillObject,
            backgroundColor: theme.colors.text,
            borderRadius: theme.radii.$3,
            zIndex: -1,
            transform: [
              { translateX: theme.spacing.$2 },
              { translateY: translateY },
            ],
          }}
        />
      )}
      <View
        style={[
          {
            alignSelf: "stretch",
            borderRadius: theme.radii.$3,
            borderWidth: 2,
            borderColor: theme.colors.text,
            backgroundColor: "#f31212",
            zIndex: 1,
          },
          style,
        ]}
      >
        <TouchableOpacity
          onPress={onPress}
          activeOpacity={0.7}
          style={{
            alignSelf: "stretch",
            flexDirection: "row",
            justifyContent: "center",
            alignItems: "center",
            paddingVertical: theme.spacing.$4,
          }}
        >
          {children}
        </TouchableOpacity>
      </View>
    </View>
  );
};
