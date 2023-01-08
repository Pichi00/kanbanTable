import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { Text, View } from "react-native";
import { ScreenContainer } from "../../../components";
import {
  AppParamList,
  AppRoutes,
  RootStackParamList,
  RootStackRoutes,
} from "../../../navigation/types";
import { useTheme } from "../../../theme";
import { DrawerScreenProps } from "@react-navigation/drawer/lib/typescript/src/types";

type Props = DrawerScreenProps<AppParamList, typeof AppRoutes["Table"]>;

export const TableScreen = ({ navigation }: Props) => {
  const { theme } = useTheme();

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
      }}
    ></ScreenContainer>
  );
};
