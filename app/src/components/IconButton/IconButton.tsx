import { Button, ButtonProps } from "../Button";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { Text } from "react-native";
import { useTheme } from "../../theme";

type Props = ButtonProps & {
  children: string;
  icon: keyof typeof MaterialCommunityIcons.glyphMap;
};

export const IconButton = ({ children, icon, onPress }: Props) => {
  const { theme } = useTheme();

  return (
    <Button
      onPress={() => {}}
      style={{
        flexDirection: "row",
        justifyContent: "center",
        paddingHorizontal: theme.spacing.$5,
        paddingVertical: theme.spacing.$4,
      }}
    >
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.button,
          fontWeight: "bold",
          fontVariant: ["small-caps"],
          marginRight: theme.spacing.$4,
        }}
      >
        {children}
      </Text>
      <MaterialCommunityIcons size={24} name={icon} color={theme.colors.text} />
    </Button>
  );
};
