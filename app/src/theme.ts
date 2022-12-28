import { Theme } from "./context/theme/ThemeContext";
import { createTheme } from "./context/theme/themeFactory";

export const theme = {
  colors: {
    background: "#F5EBE0",
    surface: "#E3D5CA",
    surfaceDark: "#D5BDAF",

    text: "#1E1510",
  },
  spacing: {
    $1: 2,
    $2: 4,
    $3: 8,
    $4: 16,
    $5: 24,
    $6: 32,
  },
  radii: {
    $1: 2,
    $2: 4,
    $3: 8,
    $4: 16,
    $5: 24,
  },
  fontSizes: {
    display: 48,
    title: 32,
    body: 16,
  },
} satisfies Theme;

const { ThemeContext, ThemeProvider, useTheme } = createTheme<typeof theme>();

export { ThemeContext, ThemeProvider, useTheme };
