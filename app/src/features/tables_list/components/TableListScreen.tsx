import { DrawerScreenProps } from "@react-navigation/drawer";
import { useFocusEffect } from "@react-navigation/native";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useCallback, Fragment } from "react";
import { ScrollView, Text, View } from "react-native";
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
          Łukasz!
        </Text>
      </Text>
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
      <ScrollView showsVerticalScrollIndicator={false}>
        {tablesQuery.data?.map((table) => (
          <Fragment key={table.id}>
            <TableListItem
              table={table}
              onPress={() => {
                navigation.navigate(AppRoutes.Table, {
                  tableId: table.id,
                });
              }}
            />
            <View style={{ height: theme.spacing.$5 }} />
          </Fragment>
        ))}
      </ScrollView>
      <IconButton icon="plus" onPress={handleCreateTable}>
        Add Table
      </IconButton>
    </ScreenContainer>
  );
};
