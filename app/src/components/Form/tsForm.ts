import { createTsForm } from "@ts-react/form";
import { z } from "zod";
import { ConnectedInput } from "./ConnectedInput";
import { FormContainer } from "./FormContainer";

const mapping = [[z.string(), ConnectedInput]] as const;

export const Form = createTsForm(mapping, {
  FormComponent: FormContainer,
});
