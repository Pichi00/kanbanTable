import {
  Text,
  View,
  StyleSheet,
  useWindowDimensions,
  Platform,
} from "react-native";
import { ScreenContainer } from "../../../components";
import { AppParamList, AppRoutes } from "../../../navigation/types";
import { useTheme } from "../../../theme";
import { DrawerScreenProps } from "@react-navigation/drawer/lib/typescript/src/types";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { IconButton } from "../../../components/IconButton";
import { ScrollView } from "react-native-gesture-handler";
import { useAuth } from "../../../hooks/useAuth";

type Props = DrawerScreenProps<AppParamList, typeof AppRoutes["Table"]>;

export const TableScreen = ({ navigation }: Props) => {
  const { user, logout } = useAuth();
  const { width } = useWindowDimensions();
  const { theme } = useTheme();

  const CARD_WIDTH = width - 2 * theme.spacing.$5;

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingTop: 0,
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
        <Text
          style={{
            fontSize: theme.fontSizes.subtitle,
            color: theme.colors.surfaceLight,
          }}
        >
          Table{" "}
          <Text
            style={{
              fontFamily: theme.fontFamily.title,
              color: theme.colors.text,
            }}
          >
            Dupa
          </Text>
        </Text>
        <MaterialCommunityIcons name="table" size={24} color="black" />
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
            <Text
              style={{
                fontFamily: theme.fontFamily.title,
              }}
            >
              Do zrobienia
            </Text>
            <View style={{ marginTop: "auto" }} />
            <IconButton onPress={() => {}} icon="plus" noShadow>
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
        <View
          style={{
            width: CARD_WIDTH,
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
            <Text
              style={{
                fontFamily: theme.fontFamily.title,
              }}
            >
              Do zrobienia
            </Text>
            <View style={{ marginTop: "auto" }} />
            <IconButton onPress={() => {}} icon="plus" noShadow>
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
      </ScrollView>
      <View
        style={{
          marginTop: theme.spacing.$5,
        }}
      />
      <IconButton onPress={logout} icon="logout">
        Logout
      </IconButton>
    </ScreenContainer>
  );
};
