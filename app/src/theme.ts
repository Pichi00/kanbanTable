import { Theme } from "./context/theme/ThemeContext";
import { createTheme } from "./context/theme/themeFactory";

export const theme = {
  colors: {
    background: "#FFF5F6",
    surface: "#4B3030",
    surfaceDark: "#0C0606",
    surfaceLight: "#B39999",

    text: "#000",

    accent: "#F31212",
  },
  spacing: {
    $1: 2,
    $2: 4,
    $3: 8,
    $4: 16,
    $5: 24,
    $6: 32,
    $7: 40,

    $inputContainer: 64,
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
    subtitle: 20,
    body: 16,
    button: 16,
  },
  fontFamily: {
    display: "Montserrat_900Black",
    title: "Montserrat_700Bold",
    regular: "Montserrat_400Regular",
    asulRegular: "Asul_400Regular",
    asulBold: "Asul_700Bold",
    body: undefined,
  },
} satisfies Theme;

type AppTheme = typeof theme;

const { ThemeContext, ThemeProvider, useTheme } = createTheme<typeof theme>();

export { ThemeContext, ThemeProvider, useTheme };
export type { AppTheme };
