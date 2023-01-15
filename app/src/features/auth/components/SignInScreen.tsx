import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList, RootStackRoutes } from "../../../navigation/types";
import { Text, View } from "react-native";
import { ScreenContainer } from "../../../components";
import { useTheme } from "../../../theme";
import { SignInForm, type SignInSchemaType } from "./SignInForm";
import { useAuth } from "../../../hooks/useAuth";
import { AppAPI } from "../../../api";

type Props = NativeStackScreenProps<
  RootStackParamList,
  typeof RootStackRoutes["SignIn"]
>;

export const SignInScreen = ({ navigation, route }: Props) => {
  const { setToken } = useAuth();
  const { theme } = useTheme();

  const handleSubmit = async ({ email, password }: SignInSchemaType) => {
    try {
      const token = await AppAPI.auth.signInEmailAndPassword({
        email,
        password,
      });
      setToken(token);
    } catch (error) {
      // TODO: Handle error
      console.error(error);
    }
  };

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        paddingTop: 0,
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
        <SignInForm onSubmit={handleSubmit} />
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
