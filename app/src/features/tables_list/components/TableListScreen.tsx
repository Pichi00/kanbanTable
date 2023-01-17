import { DrawerScreenProps } from "@react-navigation/drawer";
import { useFocusEffect } from "@react-navigation/native";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useCallback, Fragment } from "react";
import { ScrollView, Text, View, FlatList } from "react-native";
import { AppAPI } from "../../../api";
import { ScreenContainer } from "../../../components";
import { IconButton } from "../../../components/IconButton";
import { useAuth } from "../../../hooks/useAuth";
import { AppParamList, AppRoutes } from "../../../navigation/types";
import { useTheme } from "../../../theme";
import { TableListItem } from "./TableListItem";

type Props = DrawerScreenProps<AppParamList, typeof AppRoutes["Table"]>;

export const TableListScreen = ({ navigation }: Props) => {
  const { theme } = useTheme();
  const { user } = useAuth();

  const tablesQuery = useQuery(["tables"], AppAPI.app.getTables);
  const createTableMutation = useMutation(AppAPI.app.createTable);

  useFocusEffect(
    useCallback(() => {
      tablesQuery.refetch();
    }, [tablesQuery]),
  );

  const handleCreateTable = async () => {
    const table = await createTableMutation.mutateAsync();

    navigation.navigate(AppRoutes.Table, {
      tableId: table.id,
    });
  };

  if (tablesQuery.isLoading) {
    return <Text>Loading...</Text>;
  }

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingTop: 0,
      }}
    >
      <Text
        style={{
          fontSize: theme.fontSizes.display,
        }}
      >
        Hi,{" "}
        <Text
          style={{
            fontWeight: "bold",
          }}
        >
          {user.name}!
        </Text>
      </Text>

      {tablesQuery.data?.length === 0 ? (
        <View
          style={{
            marginBottom: "auto",
            flex: 1,
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Text
            style={{
              fontSize: theme.fontSizes.subtitle,
              fontFamily: theme.fontFamily.regular,
              color: theme.colors.text,
            }}
          >
            You don't have any tables! 😭
          </Text>
        </View>
      ) : (
        <>
          <Text
            style={{
              fontSize: theme.fontSizes.title,
              fontFamily: theme.fontFamily.body,
              marginTop: theme.spacing.$4,
              marginBottom: theme.spacing.$3,
            }}
          >
            Your tables
          </Text>
          <FlatList
            onRefresh={tablesQuery.refetch}
            refreshing={tablesQuery.isLoading}
            showsVerticalScrollIndicator={false}
            style={{
              flex: 1,
              overflow: "visible",
              alignSelf: "stretch",
              marginBottom: theme.spacing.$4,
            }}
            data={tablesQuery.data}
            keyExtractor={(item) => item.id.toString()}
            renderItem={({ item }) => (
              <>
                <TableListItem
                  table={item}
                  onPress={() => {
                    navigation.navigate(AppRoutes.Table, {
                      tableId: item.id,
                    });
                  }}
                />
                <View style={{ height: theme.spacing.$5 }} />
              </>
            )}
            ItemSeparatorComponent={() => (
              <View style={{ height: theme.spacing.$4 }} />
            )}
          />
        </>
      )}

      <IconButton icon="plus" onPress={handleCreateTable}>
        Add Table
      </IconButton>
    </ScreenContainer>
  );
};
