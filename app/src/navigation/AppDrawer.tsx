import { createDrawerNavigator } from "@react-navigation/drawer";
import { AppBar } from "../components/AppBar";
import { TableScreen } from "../features/table";
import { TableListScreen } from "../features/tables_list";
import { AppRoutes } from "./types";

const DrawerNavigator = createDrawerNavigator();

export const AppDrawerNavigator = () => {
  return (
    <DrawerNavigator.Navigator
      // drawerContent={(props) => <></>}
      screenOptions={{
        header: (props) => <AppBar {...props} />,
        drawerType: "slide",
      }}
    >
      <DrawerNavigator.Screen
        name={AppRoutes.TableList}
        component={TableListScreen}
      />
      <DrawerNavigator.Screen name={AppRoutes.Table} component={TableScreen} />
    </DrawerNavigator.Navigator>
  );
};
