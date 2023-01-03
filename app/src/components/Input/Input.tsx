import { ComponentProps, useCallback, useRef, useState } from "react";
import { Text, TextInput, TouchableWithoutFeedback, View } from "react-native";
import Animated, {
  runOnJS,
  useAnimatedStyle,
  useSharedValue,
  withTiming,
} from "react-native-reanimated";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { useTheme } from "../../theme";
import { Gesture, GestureDetector } from "react-native-gesture-handler";

type InputIcon = {
  icon: keyof typeof MaterialCommunityIcons.glyphMap;
  onPress?: () => void;
};

type Props = ComponentProps<typeof TextInput> & {
  prefix?: InputIcon;
  suffix?: InputIcon;
};

export const Input = ({
  style,
  prefix,
  placeholder,
  suffix,
  ...props
}: Props) => {
  const { theme } = useTheme();
  const [value, setValue] = useState("");
  const isFocused = useSharedValue(false);
  const inputRef = useRef<TextInput>();

  const focus = useCallback(() => {
    inputRef.current?.focus();
  }, [inputRef]);

  const handleChange = useCallback(
    (text: string) => {
      setValue(text);
    },
    [setValue],
  );

  const rPlaceholderStyles = useAnimatedStyle(() => {
    const isFocusedOrNotEmpty = isFocused.value || value !== "";

    return {
      transform: [
        {
          translateY: withTiming(isFocusedOrNotEmpty ? -20 : 0),
        },
      ],
      fontSize: withTiming(isFocusedOrNotEmpty ? 12 : theme.fontSizes.body),
    };
  }, [value]);

  return (
    <TouchableWithoutFeedback onPress={focus}>
      <Animated.View
        style={[
          {
            flexDirection: "row",
            alignSelf: "stretch",
            alignItems: "center",
            paddingHorizontal: theme.spacing.$4,
            backgroundColor: theme.colors.surfaceLight,
            borderRadius: theme.radii.$3,
            overflow: "hidden",
          },
        ]}
      >
        {prefix && (
          <MaterialCommunityIcons
            name={prefix.icon}
            size={24}
            color={theme.colors.surfaceDark}
            onPress={prefix.onPress}
            // style={{ position: "absolute", left: 10, top: 10 }}
          />
        )}
        <View
          style={{
            flex: 1,
            marginHorizontal: theme.spacing[prefix ? "$4" : "$3"],
            paddingVertical: theme.spacing.$5,
            justifyContent: "center",
          }}
        >
          <TextInput
            ref={inputRef}
            value={value}
            onChangeText={handleChange}
            onFocus={() => {
              isFocused.value = true;
            }}
            onBlur={() => {
              isFocused.value = false;
            }}
            style={[
              {
                alignSelf: "stretch",
                fontSize: theme.fontSizes.body,
                color: theme.colors.surfaceDark,
              },
              style,
            ]}
            placeholderTextColor={theme.colors.surfaceDark}
            {...props}
          />
          <Animated.Text
            style={[
              {
                position: "absolute",
                left: 0,
                color: theme.colors.surfaceDark,
              },
              rPlaceholderStyles,
            ]}
          >
            {placeholder}
          </Animated.Text>
        </View>
        {suffix && (
          <MaterialCommunityIcons
            name={suffix.icon}
            size={24}
            color={theme.colors.surfaceDark}
            onPress={suffix.onPress}
          />
        )}
      </Animated.View>
    </TouchableWithoutFeedback>
  );
};
