import { createContext, PropsWithChildren, useReducer, useEffect } from "react";
import * as SecureStore from "expo-secure-store";
import { decodeUser, JwtUser } from "../utils/decodeUser";

type AuthContextType = {
  user: JwtUser | null;
  isAuthenticated: boolean;
  setToken: (token: string) => void;
  logout: () => void;
};

export const AuthContext = createContext<AuthContextType | null>(null);

const initialState = {
  user: null,
  isAuthenticated: false,
};

type AuthAction =
  | {
      type: "SIGN_IN";
      payload: {
        user: JwtUser;
      };
    }
  | {
      type: "SIGN_OUT";
    };

const authReducer = (state: typeof initialState, action: AuthAction) => {
  switch (action.type) {
    case "SIGN_IN":
      return {
        ...state,
        user: action.payload.user,
        isAuthenticated: true,
      };
    case "SIGN_OUT":
      return {
        ...state,
        user: null,
        isAuthenticated: false,
      };
  }
};

export const AuthProvider = ({ children }: PropsWithChildren) => {
  const [auth, dispatch] = useReducer(authReducer, initialState);

  useEffect(() => {
    const getToken = async () => {
      const token = await SecureStore.getItemAsync("token");

      if (token) {
        const user = decodeUser(token);
        if (user.expiringAt < Date.now())
          dispatch({ type: "SIGN_IN", payload: { user } });
      } else {
        await SecureStore.deleteItemAsync("token");
        dispatch({ type: "SIGN_OUT" });
      }
    };

    getToken();
  }, []);

  const setToken = async (token: string) => {
    await SecureStore.setItemAsync("token", token);
    const user = decodeUser(token);
    dispatch({ type: "SIGN_IN", payload: { user } });
  };

  const logout = async () => {
    await SecureStore.deleteItemAsync("token");
    dispatch({ type: "SIGN_OUT" });
  };

  return (
    <AuthContext.Provider value={{ ...auth, setToken, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
