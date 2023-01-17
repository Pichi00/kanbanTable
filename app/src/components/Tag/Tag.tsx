import { type Tag as TagType } from "../../api/types";
import { View, Text } from "react-native";
import { useTheme } from "../../theme";
import invert from "invert-color";

type Props = {
  tag: TagType;
};

export const Tag = ({ tag }: Props) => {
  const { theme } = useTheme();

  return (
    <View
      style={{
        backgroundColor: tag.color,
        borderRadius: theme.radii.$5,
        paddingVertical: theme.spacing.$3,
        paddingHorizontal: theme.spacing.$5,
        justifyContent: "center",
        alignItems: "center",
        alignSelf: "flex-start",
      }}
    >
      <Text
        style={{
          color: invert(tag.color, true),
          fontFamily: theme.fontFamily.title,
        }}
      >
        {tag.name}
      </Text>
    </View>
  );
};
