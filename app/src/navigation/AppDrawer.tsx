import { createDrawerNavigator } from "@react-navigation/drawer";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { AppBar } from "../components/AppBar";
import { TableScreen } from "../features/table";
import { TableListScreen } from "../features/tables_list";
import { TableSettingsScreen } from "../features/table_settings_screen";
import { AppRoutes } from "./types";

const DrawerNavigator = createNativeStackNavigator();

export const AppDrawerNavigator = () => {
  return (
    <DrawerNavigator.Navigator
      // drawerContent={(props) => <></>}
      screenOptions={{
        header: (props) => <AppBar {...props} />,
        // drawerType: "slide",
      }}
    >
      <DrawerNavigator.Screen
        name={AppRoutes.TableList}
        component={TableListScreen}
      />
      <DrawerNavigator.Screen name={AppRoutes.Table} component={TableScreen} />
      <DrawerNavigator.Screen
        name={AppRoutes.TableSettingsScreen}
        component={TableSettingsScreen}
      />
    </DrawerNavigator.Navigator>
  );
};
