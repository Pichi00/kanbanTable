import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Fragment, useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  useWindowDimensions,
  ScrollView,
  TextInput,
  ActivityIndicator,
  FlatList,
} from "react-native";
import { AppAPI } from "../../../api";
import { TaskGroupType } from "../../../api/types";
import { FullscreenModal } from "../../../components/FullscreenModal";
import { IconButton } from "../../../components/IconButton";
import { useTheme } from "../../../theme";
import { Task } from "./Task";
import { TaskDetailsModalContent } from "./TaskDetailsModalContent";
import MaterialCommunityIcons from "@expo/vector-icons/MaterialCommunityIcons";

type Props = {
  taskGroup: TaskGroupType;
  tableId: number;
};

export const TaskGroup = ({ taskGroup, tableId }: Props) => {
  const [selectedTask, setSelectedTask] = useState<number>(null);
  const queryClient = useQueryClient();
  const { width } = useWindowDimensions();
  const { theme } = useTheme();

  const CARD_WIDTH = width - 2 * theme.spacing.$5;

  const taskGroupQuery = useQuery(["taskgroup", taskGroup.id], () =>
    AppAPI.app.getTaskGroup(taskGroup.id),
  );

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
      queryClient.invalidateQueries(["taskgroup", taskGroup.id]);
    },
  });

  const addTaskMutation = useMutation({
    mutationFn: (taskGroupId: number) => AppAPI.app.createTask(taskGroupId),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
      queryClient.invalidateQueries(["taskgroup", taskGroup.id]);
    },
  });

  const deleteTaskGroupMutation = useMutation({
    mutationFn: (taskGroupId: number) =>
      AppAPI.app.deleteTaskGroup(taskGroupId),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
    },
  });

  if (taskGroupQuery.isLoading) {
    return null;
  }

  return (
    <View
      style={{
        width: CARD_WIDTH,
        marginRight: theme.spacing.$5,
      }}
    >
      <FullscreenModal
        visible={selectedTask !== null}
        onClose={() => {
          setSelectedTask(null);
        }}
      >
        <TaskDetailsModalContent
          taskId={selectedTask}
          tableId={tableId}
          taskGroupId={taskGroup.id}
          onDeleted={() => {
            setSelectedTask(null);
          }}
        />
      </FullscreenModal>
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
              flex: 1,
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
          <MaterialCommunityIcons
            name="delete"
            size={24}
            color={theme.colors.text}
            onPress={() => {
              deleteTaskGroupMutation.mutate(taskGroup.id);
            }}
          />
        </View>
        <FlatList
          data={taskGroupQuery.data.tasks}
          onRefresh={() => taskGroupQuery.refetch()}
          refreshing={taskGroupQuery.isLoading}
          keyExtractor={(item) => item.id.toString()}
          showsVerticalScrollIndicator={false}
          renderItem={({ item }) => (
            <Fragment key={item.id}>
              <Task
                task={item}
                onPress={(id) => {
                  setSelectedTask(id);
                }}
              />
              <View style={{ marginBottom: theme.spacing.$4 }} />
            </Fragment>
          )}
        />
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
