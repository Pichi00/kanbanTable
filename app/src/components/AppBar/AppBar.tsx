import { View, Text, Pressable, TouchableOpacity } from "react-native";
import { type DrawerHeaderProps } from "@react-navigation/drawer";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { useTheme } from "../../theme";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { useAuth } from "../../hooks/useAuth";
import { NativeStackHeaderProps } from "@react-navigation/native-stack";

export const AppBar = ({ navigation }: NativeStackHeaderProps) => {
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
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.body,
          fontFamily: theme.fontFamily.asulBold,
        }}
      >
        Dzbanban
      </Text>
      <MaterialCommunityIcons
        onPress={logout}
        name="logout"
        color={theme.colors.text}
        size={24}
      />
    </View>
  );
};
