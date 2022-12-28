import { Theme } from "./context/theme/ThemeContext";
import { createTheme } from "./context/theme/themeFactory";

export const theme = {
  colors: {
    background: "#F5EBE0",
  },
} satisfies Theme;

const { ThemeContext, ThemeProvider, useTheme } = createTheme<typeof theme>();

export { ThemeContext, ThemeProvider, useTheme };
