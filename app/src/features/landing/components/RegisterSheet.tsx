import { useMemo } from "react";
import { Text, View, Keyboard } from "react-native";
import {
  Gesture,
  GestureDetector,
  TextInput,
  TouchableWithoutFeedback,
} from "react-native-gesture-handler";
import { BottomSheet } from "../../../components/BottomSheet";
import { Button } from "../../../components/Button";
import { Input } from "../../../components/Input";
import { useTheme } from "../../../theme";

export const RegisterSheet = () => {
  const { theme } = useTheme();

  return (
    <BottomSheet>
      <View
        style={{
          paddingTop: theme.spacing.$6,
          alignItems: "center",
        }}
      >
        <Text
          style={{
            color: theme.colors.text,
            fontSize: theme.fontSizes.title,
            fontWeight: "bold",
            marginBottom: theme.spacing.$7,
          }}
        >
          Join dzbanban
        </Text>
        <Input
          style={{
            marginBottom: theme.spacing.$5,
          }}
          placeholder="Email Address"
          keyboardType="email-address"
          autoCapitalize="none"
          autoComplete="email"
        />
        <Input
          style={{
            marginBottom: theme.spacing.$5,
          }}
          placeholder="Name"
          autoComplete="name"
        />
        <Input
          style={{
            marginBottom: theme.spacing.$5,
          }}
          placeholder="Password"
          secureTextEntry
          autoComplete="password-new"
        />
        <Button onPress={() => {}}>
          <Text>Create an Account</Text>
        </Button>
      </View>
    </BottomSheet>
  );
};
