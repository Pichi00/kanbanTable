import { useTsController } from "@ts-react/form";
import { ComponentProps } from "react";
import { Text, View } from "react-native";
import { useTheme } from "../../theme";
import { Input } from "../Input";

type Props = ComponentProps<typeof Input>;

export const ConnectedInput = ({ ...props }: Props) => {
  const { theme } = useTheme();
  const { field, error } = useTsController<string>();

  return (
    <>
      <Input
        value={field.value ? field.value : ""}
        defaultValue={field.value ? field.value : ""}
        onChangeText={field.onChange}
        shadowColor={error ? theme.colors.accent : undefined}
        {...props}
      />
      <View
        style={{
          paddingTop: theme.spacing.$3,
          paddingBottom: theme.spacing.$4,
          alignSelf: "flex-end",
        }}
      >
        {error && (
          <Text
            style={{
              color: theme.colors.accent,
              fontFamily: theme.fontFamily.title,
            }}
          >
            {error.errorMessage}
          </Text>
        )}
      </View>
    </>
  );
};
