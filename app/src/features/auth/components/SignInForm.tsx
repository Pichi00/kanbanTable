import { zodResolver } from "@hookform/resolvers/zod";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Form } from "../../../components/Form";

const LoginSchema = z.object({
  email: z.string().email("Invalid email address"),
  password: z.string(),
});

type SchemaType = z.infer<typeof LoginSchema>;

type Props = {
  onSubmit: (data: SchemaType) => void;
};

export const SignInForm = ({ onSubmit }: Props) => {
  const [passwordVisible, setPasswordVisible] = useState(false);
  const form = useForm<SchemaType>({
    resolver: zodResolver(LoginSchema),
    reValidateMode: "onSubmit",
  });

  return (
    <Form
      form={form}
      schema={LoginSchema}
      onSubmit={onSubmit}
      props={{
        email: {
          placeholder: "Email Address",
          autoComplete: "email",
          keyboardType: "email-address",
          autoCapitalize: "none",
          prefix: { icon: "email" },
        },
        password: {
          placeholder: "Password",
          autoComplete: "password",
          prefix: { icon: "form-textbox-password" },
          suffix: {
            icon: passwordVisible ? "eye" : "eye-off",
            onPress: () => setPasswordVisible((prev) => !prev),
          },
          secureTextEntry: !passwordVisible,
        },
      }}
      formProps={{
        button: {
          label: "Sign In",
          icon: "login",
        },
      }}
    />
  );
};

export type { SchemaType as SignInSchemaType };
