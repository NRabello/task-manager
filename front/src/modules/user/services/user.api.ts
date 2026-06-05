import { api } from "@/shared/lib/api/axios";
import { RegisterDTO } from '../dtos/register.dto';
import { LoginDTO } from '../dtos/login.dto';

export async function registerUser(user: RegisterDTO) {
  try {
    const response = await api.post('/user/register', user);
    return response.data;
  } catch (error) {
    throw error;
  }
};

interface ApiResponse<T> {
  data: T;
  status: number;
  message: string;
}

export async function loginUser(user: LoginDTO): Promise<string> {
  const response = await api.post<ApiResponse<string>>('/user/login', user);
  return response.data.data;
}