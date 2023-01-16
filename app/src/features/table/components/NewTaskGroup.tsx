import {
  View,
  useWindowDimensions,
  Text,
  Pressable,
  StyleSheet,
} from "react-native";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { useTheme } from "../../../theme";

type Props = {
  onPress: () => void;
};

export const NewTaskGroup = ({ onPress }: Props) => {
  const { theme } = useTheme();
  const { width } = useWindowDimensions();

  const WIDTH = width - theme.spacing.$5 * 2;

  return (
    <Pressable onPress={onPress}>
      <View
        style={{
          ...StyleSheet.absoluteFillObject,
          backgroundColor: theme.colors.surfaceDark,
          borderRadius: theme.radii.$4,
          // transform: [{ translateY: theme.spacing.$3 }],
        }}
      />
      <View
        style={{
          flex: 1,
          width: WIDTH,
          justifyContent: "center",
          alignItems: "center",
          borderWidth: 2,
          borderColor: theme.colors.text,
          borderRadius: theme.radii.$3,
          backgroundColor: theme.colors.background,
        }}
      >
        <View
          style={{
            flexDirection: "row",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <MaterialCommunityIcons
            name="note-plus-outline"
            size={32}
            color={theme.colors.text}
          />
          <Text
            style={{
              fontSize: theme.fontSizes.subtitle,
              fontFamily: theme.fontFamily.title,
              color: theme.colors.text,
              marginLeft: theme.spacing.$4,
            }}
          >
            Add Card
          </Text>
        </View>
      </View>
    </Pressable>
  );
};
