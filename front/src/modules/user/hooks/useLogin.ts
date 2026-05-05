import { loginUser } from "../services/user.api";
import { LoginDTO } from "../dtos/login.dto";

export function useLogin() {
  const login = async (data: LoginDTO) => {
    const response = await loginUser(data);
    console.log(response);
  };

  return { login };
}