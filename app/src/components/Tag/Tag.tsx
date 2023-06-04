import { type Tag as TagType } from "../../api/types";
import { View, Text } from "react-native";
import { useTheme } from "../../theme";
import invert from "invert-color";

type Props = {
  tag: TagType;
  fill?: boolean;
};

export const Tag = ({ tag, fill = true }: Props) => {
  const { theme } = useTheme();

  return (
    <View
      style={{
        borderRadius: theme.radii.$5,
        paddingVertical: theme.spacing.$3,
        paddingHorizontal: theme.spacing.$5,
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        alignSelf: "flex-start",
        borderWidth: 2,
        borderColor: tag.color,
        ...(fill
          ? {
              backgroundColor: tag.color,
            }
          : {
              borderColor: theme.colors.surface,
            }),
      }}
    >
      <Text
        style={{
          color: invert(tag.color, true),
          fontFamily: theme.fontFamily.title,
          ...(!fill && {
            color: theme.colors.surface,
          }),
        }}
      >
        {tag.name}
      </Text>
      {!fill && (
        <View
          style={{
            width: 16,
            height: 16,
            borderRadius: 16,
            backgroundColor: tag.color,
            marginLeft: theme.spacing.$3,
            borderColor: theme.colors.surface,
            borderWidth: 1,
          }}
        />
      )}
    </View>
  );
};
