import { registerUser } from "../services/user.api";
import { RegisterDTO } from "../dtos/register.dto";

export function useRegister() {
  const register = async (data: RegisterDTO) => {
    const response = await registerUser(data);
    console.log(response);
  };

  return { register };
}