import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList, RootStackRoutes } from "../../../navigation/types";
import { Text, View } from "react-native";
import { ScreenContainer } from "../../../components";
import { useTheme } from "../../../theme";
import { RegisterForm, type RegisterSchemaType } from "./RegisterForm";
import { AppAPI } from "../../../api";

type Props = NativeStackScreenProps<
  RootStackParamList,
  typeof RootStackRoutes["Register"]
>;

export const RegisterScreen = ({ navigation, route }: Props) => {
  const { theme } = useTheme();

  const handleSubmit = async ({
    email,
    name,
    password,
  }: RegisterSchemaType) => {
    try {
      await AppAPI.auth.register({ email, name, password });
      navigation.replace(RootStackRoutes.SignIn);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingTop: 0,
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
        <RegisterForm onSubmit={handleSubmit} />
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
