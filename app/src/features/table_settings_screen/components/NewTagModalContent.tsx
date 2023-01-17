import { z } from "zod";
import { View, Text } from "react-native";
import { Form } from "../../../components/Form";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useTheme } from "../../../theme";

const NewTagSchema = z.object({
  name: z.string().min(1).max(20),
  color: z
    .string()
    .min(1)
    .max(20)
    .regex(/^#[0-9a-fA-F]{6}$/, "Use a valid hex color code (e.g. #05FBE0)"),
});

type SchemaType = z.infer<typeof NewTagSchema>;

type Props = {
  onSubmit: (data: SchemaType) => void;
};

export const NewTagModalContent = ({ onSubmit }: Props) => {
  const { theme } = useTheme();
  const form = useForm<SchemaType>({
    resolver: zodResolver(NewTagSchema),
    reValidateMode: "onSubmit",
  });

  return (
    <View>
      <Text
        style={{
          fontFamily: theme.fontFamily.display,
          fontSize: theme.fontSizes.title,
          marginBottom: theme.spacing.$5,
        }}
      >
        Create Tag
      </Text>
      <Form
        form={form}
        schema={NewTagSchema}
        onSubmit={onSubmit}
        props={{
          name: {
            placeholder: "Name ",
            prefix: { icon: "tag" },
          },
          color: {
            placeholder: "#color",
            prefix: { icon: "palette" },
          },
        }}
        formProps={{
          button: {
            label: "Create",
            icon: "plus",
          },
        }}
      />
    </View>
  );
};

export type { SchemaType as NewTagSchemaType };
