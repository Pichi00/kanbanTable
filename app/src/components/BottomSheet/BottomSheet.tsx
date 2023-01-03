import { Portal } from "@gorhom/portal";
import { useCallback, useEffect } from "react";
import { StyleSheet, Text, useWindowDimensions, View } from "react-native";
import { Gesture, GestureDetector } from "react-native-gesture-handler";
import Animated, {
  interpolateColor,
  runOnJS,
  useAnimatedStyle,
  useSharedValue,
  withSpring,
  WithSpringConfig,
  withTiming,
} from "react-native-reanimated";
import { clamp } from "react-native-redash";
import { useTheme } from "../../theme";

const SPRING_CONFIG = {
  damping: 20,
  mass: 1,
  stiffness: 100,
  overshootClamping: true,
} as WithSpringConfig;

type Props = {
  children?: React.ReactNode;
  onClose?: () => void;
};

export const BottomSheet = ({ children, onClose }: Props) => {
  const { height } = useWindowDimensions();
  const { theme } = useTheme();

  const offset = useSharedValue(0);
  const context = useSharedValue({ y: 0 });
  const isPressed = useSharedValue(false);

  const SHEET_HEIGHT = height * 0.85;
  const SNAP_POINT = SHEET_HEIGHT / 1.5;

  const scrollTo = useCallback((toValue: number) => {
    "worklet";
    offset.value = withSpring(toValue, SPRING_CONFIG);
  }, []);

  const gesture = Gesture.Pan()
    .onBegin(() => {
      isPressed.value = true;
      context.value = { y: offset.value };
    })
    .onUpdate((event) => {
      offset.value = context.value.y + event.translationY;
      offset.value = clamp(offset.value, 0, SHEET_HEIGHT);
    })
    .onEnd(() => {
      if (offset.value > SNAP_POINT) {
        scrollTo(SHEET_HEIGHT);
      } else {
        scrollTo(0);
      }
    })
    .onFinalize(() => {
      isPressed.value = false;
    });

  useEffect(() => {
    scrollTo(0);
  }, []);

  const rStyles = useAnimatedStyle(() => {
    return {
      transform: [
        { translateY: offset.value },
        { scale: withTiming(isPressed.value ? 0.95 : 1) },
      ],
      borderBottomLeftRadius: withTiming(isPressed.value ? theme.radii.$4 : 0),
      borderBottomRightRadius: withTiming(isPressed.value ? theme.radii.$4 : 0),
    };
  });

  const rLineStyles = useAnimatedStyle(() => {
    return {
      width: withSpring(offset.value < SNAP_POINT ? 80 : 20),
    };
  });

  const rBackdropStyles = useAnimatedStyle(() => {
    return {
      backgroundColor: interpolateColor(
        offset.value,
        [SNAP_POINT + 20, 0],
        ["rgba(0,0,0,0)", "rgba(0,0,0,0.85)"],
      ),
    };
  });

  return (
    <Portal>
      <Animated.View
        style={[
          StyleSheet.absoluteFill,
          {
            justifyContent: "flex-end",
            alignItems: "center",
            backgroundColor: "rgba(0,0,0,1)",
          },
          rBackdropStyles,
        ]}
      >
        <GestureDetector gesture={gesture}>
          <Animated.View
            style={[
              {
                backgroundColor: theme.colors.background,
                width: "100%",
                height: SHEET_HEIGHT,
                borderTopLeftRadius: theme.radii.$4,
                borderTopRightRadius: theme.radii.$4,
                padding: theme.spacing.$4,
                elevation: 4,
                shadowColor: "#000",
                shadowOffset: {
                  width: 0,
                  height: 2,
                },
                shadowOpacity: 0.23,
                shadowRadius: 2.62,
                position: "absolute",
                transform: [{ translateY: 0 }],
              },
              rStyles,
            ]}
          >
            <Animated.View
              style={[
                {
                  height: 4,
                  backgroundColor: theme.colors.text,
                  borderRadius: theme.radii.$2,
                  alignSelf: "center",
                },
                rLineStyles,
              ]}
            />
            <View style={{ flex: 1 }}>{children}</View>
          </Animated.View>
        </GestureDetector>
      </Animated.View>
    </Portal>
  );
};
