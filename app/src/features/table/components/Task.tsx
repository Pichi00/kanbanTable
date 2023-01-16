import { View, Text } from "react-native";
import { TaskType } from "../../../api/types";
import { useAuth } from "../../../hooks/useAuth";
import { useTheme } from "../../../theme";

type Props = {
  task: TaskType;
};

export const Task = ({ task }: Props) => {
  const { user } = useAuth();
  const { theme } = useTheme();

  return (
    <View
      style={{
        borderWidth: 2,
        backgroundColor: "#F5EBE0",
        borderColor: theme.colors.surface,
        borderRadius: theme.radii.$3,
        padding: theme.spacing.$4,
      }}
    >
      <Text
        style={{
          fontFamily: theme.fontFamily.title,
          fontSize: theme.fontSizes.subtitle,
        }}
      >
        {task.name}
      </Text>
      <Text
        style={{
          fontSize: theme.fontSizes.bodySmall,
        }}
      >
        Created by{" "}
        <Text
          style={{
            fontFamily: theme.fontFamily.title,
          }}
        >
          @{user.name}
        </Text>
      </Text>
      <View
        style={{
          marginTop: theme.spacing.$5,
          width: 60,
          height: 8,
          borderRadius: theme.radii.$4,
          backgroundColor: "#5FCA6A",
        }}
      ></View>
    </View>
  );
};
