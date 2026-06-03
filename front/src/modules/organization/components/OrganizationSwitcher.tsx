"use client";

import { Building2, ChevronDown, Plus, Check, Loader2 } from "lucide-react";
import { useState, useEffect, useRef } from "react";
import { Organization } from "../models/organization.model";
import { getMyOrganizations } from "../services/organization.api";

interface OrganizationSwitcherProps {
  token: string;
  currentOrganizationId: number | null;
  onOrganizationChange: (organization: Organization) => void;
  onCreateNew: () => void;
}

export function OrganizationSwitcher({
  token,
  currentOrganizationId,
  onOrganizationChange,
  onCreateNew,
}: OrganizationSwitcherProps) {
  const [organizations, setOrganizations] = useState<Organization[]>([]);
  const [loading, setLoading] = useState(true);
  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);

  const currentOrg = organizations.find(
    (org) => org.id === currentOrganizationId
  );

  useEffect(() => {
    loadOrganizations();
  }, [token]);

  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(event.target as Node)
      ) {
        setIsOpen(false);
      }
    }

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  async function loadOrganizations() {
    try {
      setLoading(true);
      const response = await getMyOrganizations(token);
      setOrganizations(response.data);
    } catch (err) {
      console.error("Failed to load organizations:", err);
    } finally {
      setLoading(false);
    }
  }

  function handleSelect(org: Organization) {
    onOrganizationChange(org);
    setIsOpen(false);
  }

  return (
    <div className="relative" ref={dropdownRef}>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="flex items-center gap-3 px-3 py-2 bg-[#111625] border border-gray-800 rounded-lg hover:border-gray-700 transition min-w-[180px]"
      >
        <div className="flex items-center justify-center w-8 h-8 bg-gradient-to-br from-cyan-500/20 to-purple-500/20 rounded-lg border border-gray-700">
          <Building2 className="w-4 h-4 text-cyan-400" />
        </div>
        <div className="flex-1 text-left">
          {loading ? (
            <Loader2 className="w-4 h-4 text-gray-400 animate-spin" />
          ) : (
            <>
              <p className="text-sm font-medium text-gray-200 truncate max-w-[120px]">
                {currentOrg?.name || "Select org"}
              </p>
              <p className="text-[10px] text-gray-500 uppercase tracking-wider">
                Organization
              </p>
            </>
          )}
        </div>
        <ChevronDown
          className={`w-4 h-4 text-gray-500 transition-transform ${isOpen ? "rotate-180" : ""}`}
        />
      </button>

      {isOpen && (
        <div className="absolute top-full left-0 mt-2 w-full min-w-[220px] bg-[#111625] border border-gray-800 rounded-lg shadow-xl z-50 overflow-hidden">
          <div className="p-2">
            <p className="text-[10px] font-bold text-gray-500 uppercase tracking-wider px-2 py-1">
              Your Organizations
            </p>
            <div className="space-y-1 mt-1">
              {organizations.map((org) => (
                <button
                  key={org.id}
                  onClick={() => handleSelect(org)}
                  className={`w-full flex items-center gap-3 px-2 py-2 rounded-md transition ${
                    org.id === currentOrganizationId
                      ? "bg-cyan-500/10 text-cyan-400"
                      : "text-gray-300 hover:bg-gray-800"
                  }`}
                >
                  <Building2 className="w-4 h-4 flex-shrink-0" />
                  <span className="flex-1 text-left text-sm truncate">
                    {org.name}
                  </span>
                  {org.id === currentOrganizationId && (
                    <Check className="w-4 h-4 flex-shrink-0" />
                  )}
                </button>
              ))}
            </div>
          </div>

          <div className="border-t border-gray-800 p-2">
            <button
              onClick={() => {
                setIsOpen(false);
                onCreateNew();
              }}
              className="w-full flex items-center gap-3 px-2 py-2 rounded-md text-gray-400 hover:bg-gray-800 hover:text-cyan-400 transition"
            >
              <Plus className="w-4 h-4" />
              <span className="text-sm">Create organization</span>
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
