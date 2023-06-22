import { createContext } from "react";

export const LoginTokenContext = createContext();

export function LoginTokenProvider({ children }) {
  const token = localStorage.getItem("token");

  return (
    <LoginTokenContext.Provider value={{ token }}>
      {children}
    </LoginTokenContext.Provider>
  );
}
