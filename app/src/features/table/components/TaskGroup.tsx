import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Fragment } from "react";
import {
  View,
  Text,
  StyleSheet,
  useWindowDimensions,
  ScrollView,
  TextInput,
  ActivityIndicator,
} from "react-native";
import { AppAPI } from "../../../api";
import { TaskGroupType } from "../../../api/types";
import { IconButton } from "../../../components/IconButton";
import { useTheme } from "../../../theme";
import { Task } from "./Task";

type Props = {
  taskGroup: TaskGroupType;
  tableId: number;
};

export const TaskGroup = ({ taskGroup, tableId }: Props) => {
  const queryClient = useQueryClient();
  const { width } = useWindowDimensions();
  const { theme } = useTheme();

  const CARD_WIDTH = width - 2 * theme.spacing.$5;

  const updateTaskGroupNameMutation = useMutation({
    mutationFn: ({
      taskGroupId,
      name,
    }: {
      taskGroupId: number;
      name: string;
    }) =>
      AppAPI.app.updateTaskGroupName(
        taskGroupId,
        name !== "" ? name : "Untitled",
      ),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
    },
  });

  const addTaskMutation = useMutation({
    mutationFn: (taskGroupId: number) => AppAPI.app.createTask(taskGroupId),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
    },
  });

  return (
    <View
      style={{
        width: CARD_WIDTH,
        marginRight: theme.spacing.$5,
      }}
    >
      <View
        style={{
          flex: 1,
          alignSelf: "stretch",
          backgroundColor: "white",
          borderColor: theme.colors.text,
          borderWidth: 2,
          borderRadius: theme.radii.$3,
          padding: theme.spacing.$5,
        }}
      >
        <View
          style={{
            flexDirection: "row",
            alignContent: "center",
            marginBottom: theme.spacing.$4,
          }}
        >
          {updateTaskGroupNameMutation.isLoading && (
            <ActivityIndicator
              style={{
                marginRight: theme.spacing.$3,
              }}
              color={theme.colors.text}
            />
          )}
          <TextInput
            style={{
              fontFamily: theme.fontFamily.title,
              alignSelf: "stretch",
            }}
            placeholderTextColor={theme.colors.surfaceLight}
            defaultValue={taskGroup.name}
            placeholder="Card Name"
            onEndEditing={(e) =>
              updateTaskGroupNameMutation.mutate({
                taskGroupId: taskGroup.id,
                name: e.nativeEvent.text,
              })
            }
          />
        </View>
        <ScrollView
          style={{
            marginBottom: theme.spacing.$4,
          }}
          showsVerticalScrollIndicator={false}
        >
          {taskGroup.tasks.map((task) => (
            <Fragment key={task.id}>
              <Task task={task} />
              <View style={{ marginBottom: theme.spacing.$4 }} />
            </Fragment>
          ))}
        </ScrollView>
        <View style={{ marginTop: "auto" }} />
        <IconButton
          onPress={() => addTaskMutation.mutate(taskGroup.id)}
          icon="plus"
          noShadow
        >
          Add Task
        </IconButton>
      </View>
      <View
        style={{
          ...StyleSheet.absoluteFillObject,
          borderRadius: theme.radii.$4,
          backgroundColor: theme.colors.text,
          zIndex: -1,
          transform: [{ translateY: theme.spacing.$3 }],
        }}
      />
    </View>
  );
};
