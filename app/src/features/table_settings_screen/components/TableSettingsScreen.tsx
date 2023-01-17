import { useState, Fragment } from "react";
import { Text, View, FlatList } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { AppParamList, AppRoutes } from "../../../navigation/types";
import { Input, ScreenContainer } from "../../../components";
import { useTheme } from "../../../theme";
import MaterialCommunityIcons from "@expo/vector-icons/MaterialCommunityIcons";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { AppAPI } from "../../../api";
import { Tag } from "../../../components/Tag";
import { FullscreenModal } from "../../../components/FullscreenModal";
import { NewTagModalContent } from "./NewTagModalContent";
import { NewUserModalContent } from "./NewUserModalContent";
import { TableRole } from "../../../api/types";
import { UserListItem } from "./UserListItem";
import { IconButton } from "../../../components/IconButton";

type Props = NativeStackScreenProps<
  AppParamList,
  typeof AppRoutes["TableSettingsScreen"]
>;

export const TableSettingsScreen = ({ route, navigation }: Props) => {
  const [tagModalVisible, setTagModalVisible] = useState(false);
  const [userModalVisible, setUserModalVisible] = useState(false);

  const queryClient = useQueryClient();
  const { theme } = useTheme();
  const { params } = route;
  const { tableId } = params;

  const tableQuery = useQuery(["table", tableId], () =>
    AppAPI.app.getTable(tableId),
  );

  const createTagMutation = useMutation({
    mutationFn: (data: { name: string; color: string }) =>
      AppAPI.app.createTag({ ...data, tableId }),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
    },
  });

  const addUserMutation = useMutation({
    mutationFn: (data: { userId: number; role: TableRole }) =>
      AppAPI.app.addUserToTable({ ...data, tableId }),
    onSuccess: () => {
      queryClient.invalidateQueries(["table", tableId]);
    },
  });

  const deleteTableMutation = useMutation({
    mutationFn: (id: number) => AppAPI.app.deleteTable(id),
    onSuccess: () => {
      queryClient.invalidateQueries(["table"]);
      navigation.replace(AppRoutes.TableList);
    },
  });

  if (tableQuery.isLoading) return <Text>Loading...</Text>;

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingTop: 0,
      }}
    >
      <FullscreenModal
        visible={tagModalVisible}
        onClose={() => {
          setTagModalVisible(false);
        }}
      >
        <NewTagModalContent
          onSubmit={({ name, color }) => {
            createTagMutation.mutate({
              name: name!,
              color: color!,
            });
            setTagModalVisible(false);
          }}
        />
      </FullscreenModal>
      <FullscreenModal
        visible={userModalVisible}
        onClose={() => {
          setUserModalVisible(false);
        }}
      >
        <NewUserModalContent
          onSubmit={(id) => {
            addUserMutation.mutate({
              userId: id!,
              role: "USER",
            });
            setUserModalVisible(false);
          }}
        />
      </FullscreenModal>
      <View
        style={{
          flexDirection: "row",
          alignItems: "center",
          justifyContent: "space-between",
          marginBottom: theme.spacing.$4,
        }}
      >
        <Text
          style={{
            fontSize: theme.fontSizes.subtitle,
            fontFamily: theme.fontFamily.title,
          }}
        >
          Tags ({tableQuery.data.tags.length})
        </Text>
        <MaterialCommunityIcons
          name="plus-circle-outline"
          size={32}
          color="black"
          onPress={() => setTagModalVisible(true)}
        />
      </View>
      <View
        style={{
          flexDirection: "row",
          flexWrap: "wrap",
        }}
      >
        {tableQuery.data.tags.map((tag) => (
          <View
            key={tag.id}
            style={{
              marginRight: theme.spacing.$4,
              marginBottom: theme.spacing.$4,
            }}
          >
            <Tag tag={tag} />
          </View>
        ))}
      </View>
      <View
        style={{
          flexDirection: "row",
          alignItems: "center",
          justifyContent: "space-between",
          marginBottom: theme.spacing.$4,
        }}
      >
        <Text
          style={{
            fontSize: theme.fontSizes.subtitle,
            fontFamily: theme.fontFamily.title,
          }}
        >
          Users ({tableQuery.data.userTableRoles.length})
        </Text>
        <MaterialCommunityIcons
          name="plus-circle-outline"
          size={32}
          color="black"
          onPress={() => setUserModalVisible(true)}
        />
      </View>
      <FlatList
        data={tableQuery.data.userTableRoles}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <Fragment key={item.id}>
            <UserListItem userTableRole={item} />
            <View style={{ marginBottom: theme.spacing.$4 }} />
          </Fragment>
        )}
      />
      <IconButton
        onPress={() => {
          deleteTableMutation.mutate(tableQuery.data.id);
        }}
        icon="delete"
      >
        Delete Table
      </IconButton>
    </ScreenContainer>
  );
};
