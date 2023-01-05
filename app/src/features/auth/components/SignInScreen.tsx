import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList, RootStackRoutes } from "../../../navigation/types";
import { Text, View } from "react-native";
import { Button, Input, ScreenContainer } from "../../../components";
import { useTheme } from "../../../theme";
import MDIcons from "@expo/vector-icons/MaterialCommunityIcons";
import { IconButton } from "../../../components/IconButton";
import { useState } from "react";

type Props = NativeStackScreenProps<
  RootStackParamList,
  typeof RootStackRoutes["SignIn"]
>;

export const SignInScreen = ({ navigation, route }: Props) => {
  const [passwordVisible, setPasswordVisible] = useState(false);
  const { theme } = useTheme();

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingVertical: theme.spacing.$5,
      }}
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
          Login to Dzbanban
        </Text>
        <Input
          placeholder="Email Address"
          autoComplete="email"
          keyboardType="email-address"
          autoCapitalize="none"
          prefix={{ icon: "email" }}
        />

        <Spacer spacing={theme.spacing.$4} />
        <Input
          placeholder="Password"
          autoComplete="password"
          prefix={{ icon: "form-textbox-password" }}
          suffix={{
            icon: passwordVisible ? "eye" : "eye-off",
            onPress: () => setPasswordVisible((prev) => !prev),
          }}
          secureTextEntry={!passwordVisible}
        />
        <Spacer spacing={theme.spacing.$4} />
        <IconButton onPress={() => {}} icon="account-plus">
          log in
        </IconButton>
        <Text
          style={{
            color: theme.colors.surface,
            fontSize: theme.fontSizes.body,
            marginTop: theme.spacing.$4,
          }}
        >
          Not a member yet?{" "}
          <Text
            onPress={() => navigation.navigate(RootStackRoutes.Register)}
            style={{
              color: theme.colors.text,
              fontWeight: "bold",
              textDecorationLine: "underline",
            }}
          >
            Register
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
