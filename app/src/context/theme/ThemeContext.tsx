import { createContext } from "react";

export type Theme = {
  colors?: {
    [key: string]: string;
  };
  spacing?: {
    [key: string]: number;
  };
  radii?: {
    [key: string]: number;
  };
  fontSizes?: {
    [key: string]: number;
  };
  fontFamily?: {
    [key: string]: string;
  };
};

export type ThemeContextType<AppTheme extends Theme = {}> = {
  mode: "light" | "dark";
  theme: AppTheme;
  darkTheme: AppTheme | undefined;
  toggleTheme: () => void;
};

// export const ThemeContext = createContext<ThemeContextType | null>(null);

export const createThemeContext = <AppTheme extends Theme>() => {
  return createContext<ThemeContextType<AppTheme> | null>(null);
};
