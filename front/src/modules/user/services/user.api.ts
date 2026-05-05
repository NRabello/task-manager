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

export async function loginUser(user: LoginDTO) {
  try {
    const response = await api.post('/user/login', user);
    return response.data;
  } catch (error) {
    throw error;
  }
};