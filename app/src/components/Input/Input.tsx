import { ComponentProps, useCallback, useRef, useState } from "react";
import {
  StyleSheet,
  Text,
  TextInput,
  TouchableWithoutFeedback,
  View,
} from "react-native";
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
  shadowColor?: string;
};

export const Input = ({
  style,
  prefix,
  placeholder,
  suffix,
  onChangeText,
  shadowColor,
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
      onChangeText?.(text);
    },
    [setValue, onChangeText],
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
      color: isFocusedOrNotEmpty
        ? shadowColor ?? theme.colors.text
        : theme.colors.text,
    };
  }, [value, shadowColor]);

  return (
    <TouchableWithoutFeedback onPress={focus}>
      <View
        style={{
          alignSelf: "stretch",
        }}
      >
        <View
          style={{
            ...StyleSheet.absoluteFillObject,
            backgroundColor: shadowColor ?? theme.colors.text,
            borderRadius: theme.radii.$3,
            zIndex: -1,
            transform: [
              {
                translateX: theme.spacing.$2,
              },
              {
                translateY: theme.spacing.$2,
              },
            ],
          }}
        />
        <View
          style={[
            {
              flexDirection: "row",
              alignItems: "center",
              paddingHorizontal: theme.spacing.$4,
              backgroundColor: "#FFF",
              borderRadius: theme.radii.$3,
              overflow: "hidden",
              borderWidth: 2,
              borderColor: theme.colors.text,
              zIndex: 1,
            },
          ]}
        >
          {prefix && (
            <MaterialCommunityIcons
              name={prefix.icon}
              size={24}
              color={theme.colors.text}
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
                  color: theme.colors.text,
                },
                style,
              ]}
              placeholderTextColor={theme.colors.text}
              {...props}
            />
            <Animated.Text
              style={[
                {
                  position: "absolute",
                  left: 0,
                  color: theme.colors.text,
                  fontWeight: "500",
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
              color={theme.colors.text}
              onPress={suffix.onPress}
            />
          )}
        </View>
      </View>
    </TouchableWithoutFeedback>
  );
};
