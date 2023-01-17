import { useMutation } from "@tanstack/react-query";
import { View, Text, Pressable } from "react-native";
import { TaskType } from "../../../api/types";
import { useAuth } from "../../../hooks/useAuth";
import { useTheme } from "../../../theme";

type Props = {
  task: TaskType;
  onPress: (id: number) => void;
};

export const Task = ({ task, onPress }: Props) => {
  const { user } = useAuth();
  const { theme } = useTheme();

  return (
    <Pressable onPress={() => onPress(task.id)}>
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
            @{task.creatorUsername}
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
    </Pressable>
  );
};
