import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList, RootStackRoutes } from "../../../navigation/types";
import { Text, View } from "react-native";
import { Button, Input, ScreenContainer } from "../../../components";
import { useTheme } from "../../../theme";
import MDIcons from "@expo/vector-icons/MaterialCommunityIcons";
import { IconButton } from "../../../components/IconButton";
import { useState } from "react";
import { RegisterForm } from "./RegisterForm";

type Props = NativeStackScreenProps<
  RootStackParamList,
  typeof RootStackRoutes["Register"]
>;

export const RegisterScreen = ({ navigation, route }: Props) => {
  const [passwordVisible, setPasswordVisible] = useState(false);
  const { theme } = useTheme();

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingVertical: theme.spacing.$5,
      }}
      hasKeyobardDismisser
    >
      <View
        style={{
          alignItems: "center",
        }}
      >
        <Text
          style={{
            color: theme.colors.text,
            fontSize: theme.fontSizes.title,
            fontWeight: "bold",
            marginTop: theme.spacing.$3,
            marginBottom: theme.spacing.$7,
          }}
        >
          Join Dzbanban
        </Text>
        <RegisterForm
          onSubmit={(data) => {
            console.log(data);
          }}
        />
        <Text
          style={{
            color: theme.colors.surface,
            fontSize: theme.fontSizes.body,
            marginTop: theme.spacing.$4,
          }}
        >
          Already have an account?{" "}
          <Text
            onPress={() => navigation.navigate(RootStackRoutes.SignIn)}
            style={{
              color: theme.colors.text,
              fontWeight: "bold",
              textDecorationLine: "underline",
            }}
          >
            Sign In
          </Text>
        </Text>
      </View>
    </ScreenContainer>
  );
};

const Spacer = ({ spacing }: { spacing: number }) => (
  <View
    style={{
      height: spacing,
    }}
  />
);
