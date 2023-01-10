import { useCallback, useState } from "react";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import {
  Image,
  StyleSheet,
  Text,
  useWindowDimensions,
  View,
} from "react-native";
import { Button } from "../../../components/Button";
import { ScreenContainer } from "../../../components/ScreenContainer";
import { useTheme } from "../../../theme";
import { RegisterSheet } from "./RegisterSheet";
import {
  AppRoutes,
  RootStackParamList,
  RootStackRoutes,
} from "../../../navigation/types";
import { BottomSheet } from "../../../components";
import { moderateScale } from "../../../utils/scaling";

const HERO_IMAGE = require("../../../../assets/images/hero.png");

type Props = NativeStackScreenProps<
  RootStackParamList,
  typeof RootStackRoutes["Landing"]
>;

export const LandingScreen = ({ navigation, route }: Props) => {
  const [sheetVisible, setSheetVisible] = useState(false);
  const { width, height } = useWindowDimensions();
  const { theme } = useTheme();

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
      }}
    >
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.display,
          alignSelf: "center",
          fontFamily: theme.fontFamily.asulBold,
        }}
      >
        Dzbanban
      </Text>
      <Image
        source={HERO_IMAGE}
        style={{
          width,
          height: undefined,
          aspectRatio: 786 / 563,
          marginTop: theme.spacing.$6,
          marginBottom: theme.spacing.$4,
          alignSelf: "center",
        }}
        resizeMode="contain"
      />
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.title,
          fontFamily: theme.fontFamily.asulBold,
        }}
      >
        Big (not only) in Japan
      </Text>
      <Text
        style={{
          textAlign: "left",
          marginVertical: theme.spacing.$4,
          color: theme.colors.text,
          fontSize: moderateScale(theme.fontSizes.body),
          fontFamily: theme.fontFamily.asulRegular,
          letterSpacing: 1.1,
        }}
      >
        Oh, when you're big in Japan, tonight Big in Japan, be tight Big in
        Japan, where the Eastern sea's so blue...
      </Text>
      <View
        style={{
          marginTop: "auto",
          alignItems: "center",
        }}
      >
        <Button onPress={() => navigation.navigate(RootStackRoutes.Register)}>
          <Text
            style={{
              color: theme.colors.text,
              fontSize: theme.fontSizes.button,
              fontWeight: "bold",
            }}
          >
            Get Started
          </Text>
        </Button>
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
        <Text
          onPress={() => {
            navigation.navigate(RootStackRoutes.App, {
              screen: AppRoutes.Table,
            });
          }}
          style={{
            color: theme.colors.surface,
            fontSize: theme.fontSizes.body,
            marginTop: theme.spacing.$4,
          }}
        >
          Table screen
        </Text>
      </View>
    </ScreenContainer>
  );
};
