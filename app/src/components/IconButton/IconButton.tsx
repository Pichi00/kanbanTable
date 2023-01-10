import { Button, ButtonProps } from "../Button";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { Text } from "react-native";
import { useTheme } from "../../theme";

type Props = ButtonProps & {
  children: string;
  icon: keyof typeof MaterialCommunityIcons.glyphMap;
};

export const IconButton = ({ children, icon, ...props }: Props) => {
  const { theme } = useTheme();

  return (
    <Button
      style={{
        flexDirection: "row",
        justifyContent: "center",
        paddingHorizontal: theme.spacing.$5,
      }}
      {...props}
    >
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.button,
          fontWeight: "bold",
          marginRight: theme.spacing.$4,
        }}
      >
        {children}
      </Text>
      <MaterialCommunityIcons size={24} name={icon} color={theme.colors.text} />
    </Button>
  );
};
