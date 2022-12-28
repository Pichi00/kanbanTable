import { Image, Text, useWindowDimensions, View } from "react-native";
import { Button } from "../../../components/Button";
import { ScreenContainer } from "../../../components/ScreenContainer";
import { useTheme } from "../../../theme";

const HERO_IMAGE = require("../../../../assets/images/hero.png");

export const LandingScreen = () => {
  const { width } = useWindowDimensions();
  const { theme } = useTheme();

  return (
    <ScreenContainer
      style={{
        backgroundColor: theme.colors.background,
        alignItems: "center",
      }}
    >
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.display,
          fontWeight: "bold",
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
          marginVertical: theme.spacing.$6,
        }}
        resizeMode="contain"
      />
      <Text
        style={{
          color: theme.colors.text,
          fontSize: theme.fontSizes.title,
          fontWeight: "bold",
        }}
      >
        Hey! Welcome
      </Text>
      <Text
        style={{
          textAlign: "center",
          marginVertical: theme.spacing.$4,
        }}
      >
        Quisque eu sem non lacus varius porttitor. Aenean nec ultricies lectus,
        in dapibus turpis.
      </Text>
      <Button>
        <Text
          style={{
            color: theme.colors.text,
            fontSize: 16,
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
          onPress={() => {}}
          style={{
            color: theme.colors.text,
            fontWeight: "bold",
            textDecorationLine: "underline",
          }}
        >
          Sign In
        </Text>
      </Text>
    </ScreenContainer>
  );
};
