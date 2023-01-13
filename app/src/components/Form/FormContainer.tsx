import { ComponentProps, ReactNode } from "react";
import { IconButton } from "../IconButton";

type ButtonProps = ComponentProps<typeof IconButton>;

type Props = {
  children: ReactNode;
  onSubmit: () => void;
  button: {
    label: string;
    icon: ButtonProps["icon"];
  };
};

export const FormContainer = ({ children, onSubmit, button }: Props) => {
  const { label, icon } = button;

  return (
    <>
      {children}
      {/* <View style={{ marginTop: "auto" }} /> */}
      <IconButton onPress={onSubmit} icon={icon}>
        {label}
      </IconButton>
    </>
  );
};
