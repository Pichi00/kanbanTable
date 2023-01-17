import { Pressable, StyleSheet, Text, View } from "react-native";
import { TableType } from "../../../api/types";
import { useTheme } from "../../../theme";

type Props = {
  table: TableType;
  onPress: () => void;
};

export const TableListItem = ({ table, onPress }: Props) => {
  const { theme } = useTheme();

  const { name } = table;

  return (
    <Pressable onPress={onPress}>
      <View
        style={{
          ...StyleSheet.absoluteFillObject,
          backgroundColor: theme.colors.text,
          borderRadius: theme.radii.$3,
          transform: [{ translateY: theme.spacing.$3 }],
        }}
      />

      <View
        style={{
          backgroundColor: "white",
          padding: theme.spacing.$4,
          borderRadius: theme.radii.$3,
          borderWidth: 2,
        }}
      >
        <Text
          style={{
            fontSize: theme.fontSizes.title,
            fontFamily: theme.fontFamily.title,
            marginBottom: theme.spacing.$4,
          }}
        >
          {name}
        </Text>
        <Text>{table.taskGroups.length} task group(s)</Text>
      </View>
    </Pressable>
  );
};
