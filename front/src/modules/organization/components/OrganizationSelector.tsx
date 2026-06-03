"use client";

import {
  Building2,
  Plus,
  ChevronRight,
  AlertCircle,
  Loader2,
  Sparkles,
} from "lucide-react";
import { useState, useEffect, FormEvent } from "react";
import { Organization } from "../models/organization.model";
import {
  createOrganization,
  getMyOrganizations,
} from "../services/organization.api";
import { TaskManagerLogo } from "@/shared/components/TaskManagerLogo";

interface OrganizationSelectorProps {
  token: string;
  onOrganizationSelected: (organization: Organization) => void;
}

export function OrganizationSelector({
  token,
  onOrganizationSelected,
}: OrganizationSelectorProps) {
  const [organizations, setOrganizations] = useState<Organization[]>([]);
  const [loading, setLoading] = useState(true);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const [newOrgName, setNewOrgName] = useState("");
  const [creating, setCreating] = useState(false);
  const [createError, setCreateError] = useState<string | null>(null);
  const [touched, setTouched] = useState(false);

  const isNameValid = newOrgName.trim().length >= 3;

  useEffect(() => {
    loadOrganizations();
  }, [token]);

  async function loadOrganizations() {
    try {
      setLoading(true);
      setError(null);
      const response = await getMyOrganizations(token);
      setOrganizations(response.data);

      if (response.data.length === 0) {
        setShowCreateForm(true);
      }
    } catch (err) {
      console.error(err);
      setError("Failed to load organizations. Please try again.");
    } finally {
      setLoading(false);
    }
  }

  async function handleCreateOrganization(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setTouched(true);
    setCreateError(null);

    if (!isNameValid) return;

    try {
      setCreating(true);
      const response = await createOrganization(
        { name: newOrgName.trim() },
        token
      );
      setOrganizations((prev) => [...prev, response.data]);
      setNewOrgName("");
      setShowCreateForm(false);
      setTouched(false);
      onOrganizationSelected(response.data);
    } catch (err) {
      console.error(err);
      setCreateError("Failed to create organization. Please try again.");
    } finally {
      setCreating(false);
    }
  }

  function handleSelectOrganization(org: Organization) {
    onOrganizationSelected(org);
  }

  const inputBase =
    "w-full px-4 py-3 bg-[#0a0f1c] border rounded-lg focus:ring-1 transition text-gray-200 text-sm outline-none";
  const inputOk = "border-gray-800 focus:ring-cyan-500 focus:border-cyan-500";
  const inputError =
    "border-rose-500/60 focus:ring-rose-500 focus:border-rose-500";

  const hasOrganizations = organizations.length > 0;

  return (
    <div className="min-h-screen bg-[#0a0f1c] text-white flex flex-col items-center justify-between p-6 relative overflow-hidden">
      <div className="absolute inset-0 z-0 bg-[linear-gradient(to_right,#ffffff0a_1px,transparent_1px),linear-gradient(to_bottom,#ffffff0a_1px,transparent_1px)] bg-[size:32px_32px]"></div>
      <div className="absolute -top-40 -left-40 w-[500px] h-[500px] bg-cyan-600/20 rounded-full blur-[120px] z-0 pointer-events-none"></div>
      <div className="absolute -bottom-40 -right-40 w-[500px] h-[500px] bg-purple-600/20 rounded-full blur-[120px] z-0 pointer-events-none"></div>

      <div className="flex-grow flex items-center justify-center container mx-auto px-4 z-10 w-full py-10">
        <div className="w-full max-w-lg bg-[#111625] border border-gray-800 rounded-2xl shadow-2xl p-8 relative overflow-hidden">
          <div className="absolute top-0 left-0 right-0 h-1 bg-gradient-to-r from-transparent via-cyan-500 to-transparent opacity-20"></div>

          <div className="flex items-center gap-3 mb-10 justify-center">
            <TaskManagerLogo />
            <span className="text-2xl font-bold tracking-tight">
              TaskManager<span className="text-cyan-400">.</span>
            </span>
          </div>

          {loading ? (
            <div className="flex flex-col items-center justify-center py-12">
              <Loader2 className="w-8 h-8 text-cyan-400 animate-spin mb-4" />
              <p className="text-gray-400 text-sm">Loading organizations...</p>
            </div>
          ) : error ? (
            <div className="text-center py-8">
              <div className="flex items-center justify-center gap-2 p-4 bg-rose-500/10 border border-rose-500/30 rounded-lg mb-4">
                <AlertCircle className="w-5 h-5 text-rose-400" />
                <p className="text-rose-400 text-sm">{error}</p>
              </div>
              <button
                onClick={loadOrganizations}
                className="text-cyan-400 hover:text-cyan-300 text-sm font-medium"
              >
                Try again
              </button>
            </div>
          ) : (
            <>
              {!showCreateForm && hasOrganizations ? (
                <>
                  <div className="mb-8 text-center">
                    <p className="text-cyan-400 text-xs font-bold tracking-widest uppercase mb-2 flex items-center justify-center gap-2">
                      <span className="w-[2px] h-4 bg-cyan-400 transform rotate-12 inline-block"></span>{" "}
                      Select Organization
                    </p>
                    <h2 className="text-2xl font-bold mb-2">
                      Choose your workspace
                    </h2>
                    <p className="text-gray-400 text-sm">
                      Select an organization to continue or create a new one.
                    </p>
                  </div>

                  <div className="space-y-3 mb-6">
                    {organizations.map((org) => (
                      <button
                        key={org.id}
                        onClick={() => handleSelectOrganization(org)}
                        className="w-full flex items-center gap-4 p-4 bg-[#0a0f1c] border cursor-pointer border-gray-800 rounded-xl hover:border-cyan-500/50 hover:bg-[#0d1220] transition group"
                      >
                        <div className="flex items-center justify-center w-12 h-12 bg-gradient-to-br from-cyan-500/20 to-purple-500/20 rounded-xl border border-gray-700 group-hover:border-cyan-500/50 transition">
                          <Building2 className="w-6 h-6 text-cyan-400" />
                        </div>
                        <div className="flex-1 text-left">
                          <h3 className="text-base font-semibold text-gray-200 group-hover:text-white transition">
                            {org.name}
                          </h3>
                          <p className="text-xs text-gray-500">Organization</p>
                        </div>
                        <ChevronRight className="w-5 h-5 text-gray-600 group-hover:text-cyan-400 transition" />
                      </button>
                    ))}
                  </div>

                  <button
                    onClick={() => setShowCreateForm(true)}
                    className="w-full flex items-center justify-center gap-2 py-3 border border-dashed cursor-pointer border-gray-700 rounded-xl text-gray-400 hover:text-cyan-400 hover:border-cyan-500/50 transition text-sm font-medium"
                  >
                    <Plus className="w-4 h-4" />
                    Create new organization
                  </button>
                </>
              ) : (
                <>
                  <div className="mb-8 text-center">
                    <div className="flex justify-center mb-6">
                      <div className="relative">
                        <div className="absolute inset-0 bg-cyan-400/20 rounded-full blur-xl"></div>
                        <div className="relative bg-gradient-to-br from-cyan-500/20 to-purple-500/20 p-4 rounded-full border border-gray-700">
                          <Sparkles className="w-8 h-8 text-cyan-400" />
                        </div>
                      </div>
                    </div>
                    <p className="text-cyan-400 text-xs font-bold tracking-widest uppercase mb-2 flex items-center justify-center gap-2">
                      <span className="w-[2px] h-4 bg-cyan-400 transform rotate-12 inline-block"></span>{" "}
                      {hasOrganizations ? "New Organization" : "Get Started"}
                    </p>
                    <h2 className="text-2xl font-bold mb-2">
                      {hasOrganizations
                        ? "Create organization"
                        : "Create your first organization"}
                    </h2>
                    <p className="text-gray-400 text-sm">
                      {hasOrganizations
                        ? "Add a new workspace to manage tasks and collaborate."
                        : "Organizations help you manage tasks, teams and projects in one place."}
                    </p>
                  </div>

                  <form
                    onSubmit={handleCreateOrganization}
                    className="space-y-5"
                    noValidate
                  >
                    <div>
                      <label
                        htmlFor="org-name"
                        className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2"
                      >
                        Organization Name
                      </label>
                      <input
                        type="text"
                        id="org-name"
                        placeholder="My Company"
                        value={newOrgName}
                        onChange={(e) => setNewOrgName(e.target.value)}
                        onBlur={() => setTouched(true)}
                        className={`${inputBase} ${touched && !isNameValid ? inputError : inputOk}`}
                      />
                      {touched && !isNameValid && (
                        <p className="text-rose-400 text-xs mt-1.5 flex items-center gap-1">
                          <AlertCircle className="w-3 h-3" /> Name must be at
                          least 3 characters.
                        </p>
                      )}
                    </div>

                    {createError && (
                      <div className="flex items-center gap-2 p-3 bg-rose-500/10 border border-rose-500/30 rounded-lg">
                        <AlertCircle className="w-4 h-4 text-rose-400 flex-shrink-0" />
                        <p className="text-rose-400 text-xs">{createError}</p>
                      </div>
                    )}

                    <button
                      type="submit"
                      disabled={!isNameValid || creating}
                      className="w-full flex items-center justify-center gap-2 py-3 bg-cyan-400 text-gray-950 font-bold rounded-lg cursor-pointer hover:bg-cyan-300 transition text-sm disabled:bg-gray-700 disabled:text-gray-500 disabled:cursor-not-allowed disabled:hover:bg-gray-700"
                    >
                      <Building2 className="w-4 h-4" />
                      {creating ? "Creating..." : "Create organization"}
                    </button>
                  </form>

                  {hasOrganizations && (
                    <div className="text-center mt-6 pt-6 border-t border-gray-800/50">
                      <button
                        onClick={() => {
                          setShowCreateForm(false);
                          setNewOrgName("");
                          setTouched(false);
                          setCreateError(null);
                        }}
                        className="text-sm text-gray-400 hover:text-cyan-400 transition"
                      >
                        Back to organization list
                      </button>
                    </div>
                  )}
                </>
              )}
            </>
          )}
        </div>
      </div>

      <footer className="w-full max-w-7xl mx-auto px-4 py-6 z-10 text-center text-xs text-gray-600 font-mono flex items-center justify-center">
        <div>&copy; 2026 TaskManager Inc.</div>
      </footer>
    </div>
  );
}
