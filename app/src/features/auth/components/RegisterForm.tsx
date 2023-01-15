import { createTsForm } from "@ts-react/form";
import { z } from "zod";
import { Input } from "../../../components";
import { useForm } from "react-hook-form";
import { Form, FormContainer } from "../../../components/Form";
import { useState } from "react";
import { ConnectedInput } from "../../../components/Form/ConnectedInput";
import { zodResolver } from "@hookform/resolvers/zod";

const RegisterSchema = z.object({
  name: z.string().min(1, "Name is required"),
  email: z.string().email("Invalid email address"),
  password: z.string().min(6, "Password must be at least 6 characters"),
});

type SchemaType = z.infer<typeof RegisterSchema>;

type Props = {
  onSubmit: (data: SchemaType) => void;
};

export const RegisterForm = ({ onSubmit }: Props) => {
  const [passwordVisible, setPasswordVisible] = useState(false);
  const form = useForm<SchemaType>({
    resolver: zodResolver(RegisterSchema),
    reValidateMode: "onSubmit",
  });

  return (
    <Form
      form={form}
      schema={RegisterSchema}
      onSubmit={onSubmit}
      props={{
        name: {
          placeholder: "Name",
          autoComplete: "name",
          prefix: { icon: "account" },
        },
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
          label: "Register",
          icon: "account-plus",
        },
      }}
    />
  );
};

export type { SchemaType as RegisterSchemaType };
