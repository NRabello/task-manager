"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { OrganizationSelector } from "@/modules/organization/components/OrganizationSelector";
import { Organization } from "@/modules/organization/models/organization.model";

export default function OrganizationPage() {
  const router = useRouter();
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (!storedToken) {
      router.push("/auth/login");
      return;
    }
    setToken(storedToken);
  }, [router]);

  function handleOrganizationSelected(organization: Organization) {
    localStorage.setItem("organizationId", String(organization.id));
    localStorage.setItem("organizationName", organization.name);
    router.push("/dashboard");
  }

  if (!token) {
    return null;
  }

  return (
    <OrganizationSelector
      token={token}
      onOrganizationSelected={handleOrganizationSelected}
    />
  );
}
