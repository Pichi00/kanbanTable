import { zodResolver } from "@hookform/resolvers/zod";
import { useQuery } from "@tanstack/react-query";
import { useForm } from "react-hook-form";
import { View, Text, TextInput } from "react-native";
import { z } from "zod";
import { AppAPI } from "../../../api";
import { Input } from "../../../components";
import { Form } from "../../../components/Form";
import { useAuth } from "../../../hooks/useAuth";
import { useTheme } from "../../../theme";

const UserSchema = z.object({
  email: z.string().email(),
  // role: z.string().enum(["ADMIN", "USER"]),
});

type SchemaType = z.infer<typeof UserSchema>;

type Props = {
  onSubmit: (id: number) => void;
};

export const NewUserModalContent = ({ onSubmit }: Props) => {
  const { user: authUser } = useAuth();
  const { theme } = useTheme();
  const form = useForm<SchemaType>({
    resolver: zodResolver(UserSchema),
  });

  const { isLoading, data } = useQuery(["users"], () => AppAPI.app.getUsers());

  if (isLoading) return <Text>Loading...</Text>;

  const handleSubmit = ({ email }: SchemaType) => {
    const potentialUser = data.find((user) => user.email === email);
    if (!potentialUser) {
      form.setError("email", {
        type: "manual",
        message: "Could not find user with this email",
      });

      return;
    }

    if (potentialUser.id === authUser.id) {
      form.setError("email", {
        type: "manual",
        message: "You cannot add yourself",
      });

      return;
    }

    onSubmit(potentialUser.id);
  };

  return (
    <View>
      <Text
        style={{
          fontFamily: theme.fontFamily.display,
          fontSize: theme.fontSizes.title,
          marginBottom: theme.spacing.$5,
        }}
      >
        Add User
      </Text>
      <Form
        form={form}
        schema={UserSchema}
        formProps={{
          button: {
            label: "Add",
            icon: "plus",
          },
        }}
        props={{
          email: {
            autoComplete: "email",
            keyboardType: "email-address",
            placeholder: "Email",
            prefix: {
              icon: "email",
            },
          },
        }}
        onSubmit={handleSubmit}
      />
    </View>
  );
};
