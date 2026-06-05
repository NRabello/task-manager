import { useState } from "react";
import { loginUser } from "../services/user.api";
import { LoginDTO } from "../dtos/login.dto";
import { useAuth } from "@/shared/contexts/AuthContext";
import { User } from "../models/user.model";

function decodeJwtPayload(token: string): User {
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
      .join("")
  );
  const payload = JSON.parse(jsonPayload);

  return {
    id: payload.id,
    username: payload.sub,
    name: payload.name,
  };
}

export function useLogin() {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const { login: authLogin } = useAuth();

  const login = async (data: LoginDTO) => {
    setIsLoading(true);
    setError(null);

    try {
      const token = await loginUser(data);
      const user = decodeJwtPayload(token);
      authLogin(token, user);
    } catch (err: unknown) {
      const errorMessage =
        err instanceof Error ? err.message : "Erro ao fazer login";
      setError(errorMessage);
      throw err;
    } finally {
      setIsLoading(false);
    }
  };

  return { login, isLoading, error };
}