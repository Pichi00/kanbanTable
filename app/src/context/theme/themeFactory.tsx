import { useContext, useEffect, useState } from "react";
import { Theme, createThemeContext, ThemeContextType } from "./ThemeContext";

type ThemeProviderProps<TTheme extends Theme> = Omit<
  ThemeContextType,
  "toggleTheme"
> & {
  children: React.ReactNode;
  theme: TTheme;
  darkTheme?: TTheme;
};

export const createUseTheme = <TTheme extends Theme>(
  ThemeContext: React.Context<ThemeContextType<TTheme> | null>,
) => {
  return () => {
    const context = useContext(ThemeContext);
    if (context === undefined) {
      throw new Error("useTheme must be used within a ThemeProvider");
    }
    return context;
  };
};

export const createThemeProvider = <TTheme extends Theme>(
  ThemeContext: React.Context<ThemeContextType<TTheme> | null>,
) => {
  return ({ children, theme, darkTheme, mode }: ThemeProviderProps<TTheme>) => {
    const [currentMode, setCurrentMode] = useState<"light" | "dark">(
      mode || "light",
    );

    const toggleTheme = () => {
      setCurrentMode((prevMode) => (prevMode === "light" ? "dark" : "light"));
    };

    useEffect(() => {
      if (mode) {
        setCurrentMode(mode);
      }
    }, [mode]);

    return (
      <ThemeContext.Provider
        value={{
          mode: currentMode,
          theme: currentMode === "light" ? theme : darkTheme || theme,
          darkTheme: darkTheme || theme,
          toggleTheme,
        }}
      >
        {children}
      </ThemeContext.Provider>
    );
  };
};

export const createTheme = <TTheme extends Theme>() => {
  const ThemeContext = createThemeContext<TTheme>();
  const useTheme = createUseTheme(ThemeContext);
  const ThemeProvider = createThemeProvider(ThemeContext);

  return {
    ThemeContext,
    useTheme,
    ThemeProvider,
  };
};
