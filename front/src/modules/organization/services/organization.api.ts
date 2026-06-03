import { api } from "@/shared/lib/api/axios";
import { CreateOrganizationDTO } from "../dtos/create-organization.dto";
import { Organization } from "../models/organization.model";

interface ApiResponse<T> {
  data: T;
  status: number;
  message: string;
}

export async function createOrganization(
  dto: CreateOrganizationDTO,
  token: string
): Promise<ApiResponse<Organization>> {
  const response = await api.post("/organization/create", dto, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}

export async function getMyOrganizations(
  token: string
): Promise<ApiResponse<Organization[]>> {
  const response = await api.get("/organization/my-organizations", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}
