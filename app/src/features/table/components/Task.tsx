import { useMutation } from "@tanstack/react-query";
import { View, Text, Pressable } from "react-native";
import { TaskType } from "../../../api/types";
import { useAuth } from "../../../hooks/useAuth";
import { useTheme } from "../../../theme";
import invert from "invert-color";

type Props = {
  task: TaskType;
  onPress: (id: number) => void;
};

export const Task = ({ task, onPress }: Props) => {
  const { user } = useAuth();
  const { theme } = useTheme();

  console.log(task);

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
        {task.tags?.length > 0 && (
          <View
            style={{
              flexDirection: "row",
              alignItems: "center",
              flexWrap: "wrap",
              marginTop: theme.spacing.$5,
            }}
          >
            {task.tags.map((tag, index) => (
              <View
                key={tag.id}
                style={{
                  marginTop: theme.spacing.$3,
                  // width: 60,
                  // height: 8,
                  paddingHorizontal: theme.spacing.$4,
                  paddingVertical: theme.spacing.$2,
                  borderRadius: theme.radii.$4,
                  backgroundColor: tag.color,
                  marginRight:
                    index === task.tags.length - 1 ? 0 : theme.spacing.$3,
                }}
              ></View>
            ))}
          </View>
        )}
      </View>
    </Pressable>
  );
};
