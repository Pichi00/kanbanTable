import { TouchableOpacity, View } from "react-native";
import { useTheme } from "../../theme";

type Props = {
  children: React.ReactNode;
};

export const Button = ({ children }: Props) => {
  const { theme } = useTheme();

  return (
    <TouchableOpacity
      onPress={() => {}}
      activeOpacity={0.7}
      style={{
        alignSelf: "stretch",
      }}
    >
      <View
        style={{
          backgroundColor: theme.colors.surfaceDark,
          justifyContent: "center",
          alignItems: "center",
          paddingVertical: theme.spacing.$4,
          borderRadius: theme.radii.$3,
        }}
      >
        {children}
      </View>
    </TouchableOpacity>
  );
};
