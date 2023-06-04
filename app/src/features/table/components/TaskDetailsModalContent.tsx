import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Text, View, TextInput, FlatList, Pressable } from "react-native";
import { AppAPI } from "../../../api";
import { useTheme } from "../../../theme";
import MaterialCommunityIcons from "@expo/vector-icons/MaterialCommunityIcons";
import {
  MultipleSelectList,
  SelectList,
} from "react-native-dropdown-select-list";
import { useEffect, useState } from "react";
import { Tag } from "../../../components/Tag";

type Props = {
  taskId: number;
  tableId: number;
  taskGroupId: number;
  onDeleted: () => void;
};

export const TaskDetailsModalContent = ({
  taskId,
  tableId,
  taskGroupId,
  onDeleted,
}: Props) => {
  const [tags, setTags] = useState<number[]>([]);
  const queryClient = useQueryClient();
  const { theme } = useTheme();

  const taskQuery = useQuery(["task", taskId], () =>
    AppAPI.app.getTask(taskId),
  );

  const tableQuery = useQuery(["table", tableId], () => {
    return AppAPI.app.getTable(tableId);
  });

  const updateTaskMutation = useMutation({
    mutationFn: ({
      name,
      description,
    }: {
      name: string;
      description: string;
    }) => {
      return AppAPI.app.updateTask({
        id: taskId,
        name,
        description: description ?? "",
      });
    },
    onSuccess: () => {
      queryClient.invalidateQueries(["task", taskId]);
      queryClient.invalidateQueries(["taskgroup", taskGroupId]);
    },
  });

  const deleteTaskMutation = useMutation({
    mutationFn: (id: number) => AppAPI.app.deleteTask(id),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
      queryClient.invalidateQueries(["taskgroup", taskGroupId]);
      onDeleted();
    },
  });

  const updateTaskGroupMutation = useMutation({
    mutationFn: (taskGroupId: number) =>
      AppAPI.app.updateTaskTaskGroup(taskId, taskGroupId),
    onSuccess: (_, newTaskGroupId) => {
      queryClient.invalidateQueries(["task", taskId]);
      queryClient.invalidateQueries(["taskgroup", taskGroupId]);
      queryClient.invalidateQueries(["taskgroup", newTaskGroupId]);
    },
  });

  const updateTaskTagsMutation = useMutation({
    mutationFn: (tagId: number) => AppAPI.app.addTagsToTask({ tagId, taskId }),
    onSuccess: () => {
      queryClient.invalidateQueries(["task", taskId]);
      queryClient.invalidateQueries(["taskgroup", taskGroupId]);
    },
  });

  if (taskQuery.isLoading) {
    return null;
  }

  const createdDate = new Date(taskQuery.data.createdDate);
  const taskGroups = tableQuery.data.taskGroups.map((taskGroup) => ({
    key: taskGroup.id,
    value: taskGroup.name,
    disabled: taskGroup.id === taskGroupId,
  }));

  console.log(taskQuery.data);

  return (
    <View>
      <View
        style={{
          flexDirection: "row",
          alignItems: "center",
        }}
      >
        <TextInput
          style={{
            fontFamily: theme.fontFamily.title,
            fontSize: theme.fontSizes.title,
            flex: 1,
          }}
          defaultValue={taskQuery.data.name}
          onEndEditing={(e) => {
            updateTaskMutation.mutate({
              name: e.nativeEvent.text,
              description: taskQuery.data.description,
            });
          }}
        />
        <MaterialCommunityIcons
          name="delete"
          size={24}
          color={theme.colors.accent}
          onPress={() => {
            deleteTaskMutation.mutate(taskId);
          }}
        />
      </View>
      <Text
        style={{
          fontFamily: theme.fontFamily.regular,
        }}
      >
        Created on{" "}
        {createdDate.toLocaleDateString("pl-PL", {
          dateStyle: "short",
        })}{" "}
        by @
        <Text
          style={{
            fontFamily: theme.fontFamily.title,
          }}
        >
          {taskQuery.data.creatorUsername}
        </Text>
      </Text>

      <SelectList
        placeholder="Select Card"
        boxStyles={{
          marginTop: theme.spacing.$5,
        }}
        data={taskGroups}
        setSelected={(selected) => {
          updateTaskGroupMutation.mutate(selected);
        }}
        defaultOption={taskGroups.find(
          (taskGroup) => taskGroup.key === taskGroupId,
        )}
      />

      {tableQuery.data?.tags.length > 0 && (
        <FlatList
          horizontal
          style={{
            marginTop: theme.spacing.$4,
          }}
          data={tableQuery.data.tags}
          showsHorizontalScrollIndicator={false}
          renderItem={({ item }) => (
            <Pressable
              style={{
                marginRight: theme.spacing.$3,
              }}
              onPress={() => {
                updateTaskTagsMutation.mutate(item.id);
              }}
            >
              <Tag
                tag={item}
                fill={taskQuery.data?.tags.some((tag) => tag.id === item.id)}
              ></Tag>
            </Pressable>
          )}
          keyExtractor={(item) => item.id.toString()}
        />
      )}

      <TextInput
        style={{
          fontFamily: theme.fontFamily.regular,
          fontSize: theme.fontSizes.body,
          marginTop: theme.spacing.$5,
        }}
        defaultValue={taskQuery.data.description}
        placeholder="Add description..."
        placeholderTextColor={theme.colors.surface}
        multiline
        onEndEditing={(e) => {
          updateTaskMutation.mutate({
            name: taskQuery.data.name,
            description: e.nativeEvent.text,
          });
        }}
      />
    </View>
  );
};
