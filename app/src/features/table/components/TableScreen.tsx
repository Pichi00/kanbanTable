import {
  Text,
  View,
  StyleSheet,
  useWindowDimensions,
  Platform,
  TextInput,
  ActivityIndicator,
} from "react-native";
import { ScreenContainer } from "../../../components";
import { AppParamList, AppRoutes } from "../../../navigation/types";
import { useTheme } from "../../../theme";
import { DrawerScreenProps } from "@react-navigation/drawer/lib/typescript/src/types";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { IconButton } from "../../../components/IconButton";
import { ScrollView } from "react-native-gesture-handler";
import { useAuth } from "../../../hooks/useAuth";
import { AppAPI } from "../../../api";
import { useMutation, useQuery } from "@tanstack/react-query";
import { TaskGroup } from "./TaskGroup";
import { NewTaskGroup } from "./NewTaskGroup";
import { useRef, useCallback } from "react";
import { useFocusEffect } from "@react-navigation/native";

type Props = DrawerScreenProps<AppParamList, typeof AppRoutes["Table"]>;

export const TableScreen = ({ navigation, route }: Props) => {
  const inputRef = useRef<TextInput>();
  const { theme } = useTheme();

  const { params } = route;
  const { tableId } = params;

  const tableQuery = useQuery(["table", tableId], () =>
    AppAPI.app.getTable(tableId),
  );

  useFocusEffect(
    useCallback(() => {
      tableQuery.refetch();
    }, [tableQuery]),
  );

  const updateTableNameMutation = useMutation({
    mutationFn: (name: string) => AppAPI.app.updateTableName(tableId, name),
  });

  const addTaskGroupMutation = useMutation({
    mutationFn: (tableId: number) => AppAPI.app.createTaskGroup(tableId),
  });

  const handleChangeName = async (name: string) => {
    await updateTableNameMutation.mutateAsync(name === "" ? "Untitled" : name);
    tableQuery.refetch();
  };

  const handleAddTaskGroup = async () => {
    await addTaskGroupMutation.mutateAsync(tableId);
    tableQuery.refetch();
  };

  if (tableQuery.isLoading) {
    return <Text>Loading...</Text>;
  }

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingTop: 0,
        paddingBottom: 0,
      }}
      hasKeyobardDismisser={false}
    >
      <View
        style={{
          flexDirection: "row",
          justifyContent: "space-between",
          alignItems: "center",
          marginBottom: theme.spacing.$5,
        }}
      >
        <View
          style={{
            flexDirection: "row",
            alignItems: "center",
          }}
        >
          {updateTableNameMutation.isLoading && (
            <ActivityIndicator
              style={{
                marginRight: theme.spacing.$3,
              }}
            />
          )}
          <Text
            style={{
              fontSize: theme.fontSizes.subtitle,
              color: theme.colors.surface,
            }}
          >
            Table{" "}
          </Text>
          <TextInput
            ref={inputRef}
            style={{
              fontSize: theme.fontSizes.subtitle,
              fontFamily: theme.fontFamily.title,
              color: theme.colors.text,
              flex: 1,
            }}
            placeholder="Table name"
            placeholderTextColor={theme.colors.surfaceLight}
            defaultValue={tableQuery.data?.name}
            onEndEditing={(e) => handleChangeName(e.nativeEvent.text)}
            editable={!updateTableNameMutation.isLoading}
          />
          <MaterialCommunityIcons
            name="dots-vertical"
            size={24}
            color="black"
          />
        </View>
      </View>
      <ScrollView
        horizontal
        style={{ overflow: "visible" }}
        contentContainerStyle={{
          overflow: "visible",
          paddingBottom: Platform.OS === "android" ? theme.spacing.$3 : 0,
        }}
        showsHorizontalScrollIndicator={false}
      >
        {tableQuery.data?.taskGroups.map((taskGroup) => (
          <TaskGroup
            key={taskGroup.id}
            taskGroup={taskGroup}
            tableId={tableId}
          />
        ))}
        <NewTaskGroup onPress={handleAddTaskGroup} />
      </ScrollView>
      <View
        style={{
          marginTop: theme.spacing.$5,
        }}
      />
    </ScreenContainer>
  );
};
