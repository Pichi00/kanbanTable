import { View, Text, Pressable, TouchableOpacity } from "react-native";
import { type DrawerHeaderProps } from "@react-navigation/drawer";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { useTheme } from "../../theme";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { useAuth } from "../../hooks/useAuth";

export const AppBar = ({ navigation, layout }: DrawerHeaderProps) => {
  const { logout } = useAuth();
  const insets = useSafeAreaInsets();
  const { theme } = useTheme();

  return (
    <View
      style={{
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center",
        backgroundColor: theme.colors.background,
        paddingHorizontal: theme.spacing.$5,
        paddingBottom: theme.spacing.$5,

        paddingTop: insets.top,
      }}
    >
      <TouchableOpacity
        activeOpacity={0.7}
        onPress={() => navigation.openDrawer()}
      >
        <MaterialCommunityIcons name="menu" size={24} color="black" />
      </TouchableOpacity>
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.body,
          fontFamily: theme.fontFamily.asulBold,
        }}
      >
        Dzbanban
      </Text>
      <Pressable
        style={{
          width: 24,
          height: 24,
          backgroundColor: theme.colors.surface,
          borderRadius: 12,
        }}
        onPress={logout}
      />
    </View>
  );
};
