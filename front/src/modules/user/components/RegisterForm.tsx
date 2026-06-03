"use client"

import { CheckCircle2, Bot, ShieldCheck, Check, X, AlertCircle } from 'lucide-react'
import { useState, FormEvent, useEffect } from 'react';
import { registerUser } from "../services/user.api";
import { RegisterSuccess } from "./RegisterSuccess";


const TaskManagerLogo = () => (
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" className="w-10 h-10 drop-shadow-lg" fill="none">
    <defs>
      <linearGradient id="logo-gradient-main" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#22d3ee" />
        <stop offset="100%" stopColor="#c084fc" />
      </linearGradient>

      <linearGradient id="logo-gradient-dark" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#0891b2" />
        <stop offset="100%" stopColor="#9333ea" />
      </linearGradient>
    </defs>

    <path
      d="M5 16.5 L12 23.5 L27 6.5 L22.5 4.5 L11.5 17.5 L7.5 13.5 Z"
      fill="url(#logo-gradient-main)"
    />

    <path
      d="M11 26 L15 30 L29 11.5 L25 8.5 Z"
      fill="url(#logo-gradient-dark)"
      opacity="0.8"
    />
  </svg>
)


const Requirement = ({ met, label }: { met: boolean; label: string }) => (
  <li className={`flex items-center gap-2 text-xs transition-colors ${met ? 'text-cyan-400' : 'text-gray-500'}`}>
    <span className={`flex items-center justify-center w-4 h-4 rounded-full transition-colors ${met ? 'bg-cyan-400/15' : 'bg-gray-800'}`}>
      {met
        ? <Check className="w-3 h-3" strokeWidth={3} />
        : <X className="w-3 h-3" strokeWidth={3} />}
    </span>
    <span>{label}</span>
  </li>
)


export function RegisterForm() {
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [submitError, setSubmitError] = useState<string | null>(null);

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [terms, setTerms] = useState(false);

  const [passwordFocused, setPasswordFocused] = useState(false);
  const [touched, setTouched] = useState({
    name: false,
    email: false,
    password: false,
    confirmPassword: false,
    terms: false,
  });

  const passwordChecks = {
    length: password.length >= 8,
    uppercase: /[A-Z]/.test(password),
    lowercase: /[a-z]/.test(password),
    number: /\d/.test(password),
    special: /[^A-Za-z0-9]/.test(password),
  };
  const isPasswordValid = Object.values(passwordChecks).every(Boolean);
  const isEmailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  const isNameValid = name.trim().length > 0;
  const passwordsMatch = confirmPassword.length > 0 && password === confirmPassword;
  const isFormValid = isNameValid && isEmailValid && isPasswordValid && passwordsMatch && terms;

  const showPasswordPanel = passwordFocused || password.length > 0;

  const inputBase = "w-full px-4 py-3 bg-[#0a0f1c] border rounded-lg focus:ring-1 transition text-gray-200 text-sm outline-none";
  const inputOk = "border-gray-800 focus:ring-cyan-500 focus:border-cyan-500";
  const inputError = "border-rose-500/60 focus:ring-rose-500 focus:border-rose-500";

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setTouched({ name: true, email: true, password: true, confirmPassword: true, terms: true });
    setSubmitError(null);

    if (!isFormValid) return;

    const data = {
      name: name.trim(),
      username: email.trim(),
      password,
    };

    try {
      setLoading(true);
      const response = await registerUser(data);
      console.log(response);
      setSuccess(true);
    } catch (err) {
      console.error(err);
      setSubmitError("Could not create your account. Please try again.");
    } finally {
      setLoading(false);
    }
  }

  if (success) {
    return <RegisterSuccess />;
  }

  return (
    <div className="min-h-screen bg-[#0a0f1c] text-white flex flex-col items-center justify-between p-6 relative overflow-hidden">

      <div className="absolute inset-0 z-0 bg-[linear-gradient(to_right,#ffffff0a_1px,transparent_1px),linear-gradient(to_bottom,#ffffff0a_1px,transparent_1px)] bg-[size:32px_32px]"></div>
      <div className="absolute -top-40 -left-40 w-[500px] h-[500px] bg-cyan-600/20 rounded-full blur-[120px] z-0 pointer-events-none"></div>
      <div className="absolute -bottom-40 -right-40 w-[500px] h-[500px] bg-purple-600/20 rounded-full blur-[120px] z-0 pointer-events-none"></div>

      <div className="flex-grow flex items-center justify-center container mx-auto px-4 z-10 w-full py-10">
        <div className="flex flex-col lg:flex-row items-center lg:items-stretch gap-16 w-full max-w-7xl">

          <div className="lg:w-1/2 flex flex-col justify-center py-10">
            <div>
              <div className="flex items-center gap-3 mb-10">
                <TaskManagerLogo />
                <span className="text-3xl font-bold tracking-tight">TaskManager<span className="text-cyan-400">.</span></span>
              </div>

              <div className="inline-flex items-center gap-2 rounded-full border border-gray-700 bg-gray-800/50 px-3 py-1 text-xs font-mono uppercase tracking-wider text-gray-300 backdrop-blur mb-10">
                <span className="w-2 h-2 rounded-full bg-cyan-400 animate-pulse"></span>
                v1.0 - NOW LIVE
              </div>

              <h1 className="text-6xl font-extrabold tracking-tighter leading-tight mb-8">
                <span className="bg-gradient-to-r from-gray-100 to-gray-400 bg-clip-text text-transparent">Ship work at the </span>
                <span className="bg-gradient-to-r from-cyan-400 to-purple-400 bg-clip-text text-transparent">speed of thought.</span>
              </h1>

              <p className="text-xl text-gray-400 max-w-2xl mb-12">
                The task platform engineered for high-velocity teams. Real-time sync, infinite workspaces, zero friction.
              </p>
            </div>

            <div className="space-y-6">
              {[
                { icon: CheckCircle2, title: 'Real-time engine', desc: 'Sub-50ms updates across every device.' },
                { icon: ShieldCheck, title: 'End-to-end encrypted', desc: 'Your data, your keys, your control.' },
                { icon: Bot, title: 'AI-native', desc: 'Smart prioritization built into every view.' },
              ].map((feature, i) => (
                <div key={i} className="flex items-start gap-4">
                  <feature.icon className="w-8 h-8 text-cyan-400 flex-shrink-0 mt-1" />
                  <div>
                    <h3 className="text-lg font-semibold text-gray-200">{feature.title}</h3>
                    <p className="text-gray-400 text-sm">{feature.desc}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="lg:w-1/2 flex items-center justify-center lg:justify-end">
            <div className="w-full max-w-md bg-[#111625] border border-gray-800 rounded-2xl shadow-2xl p-8 relative overflow-hidden">

              <div className="absolute top-0 left-0 right-0 h-1 bg-gradient-to-r from-transparent via-cyan-500 to-transparent opacity-20"></div>

              <div className="mb-8">
                <p className="text-cyan-400 text-xs font-bold tracking-widest uppercase mb-2 flex items-center gap-2">
                  <span className="w-[2px] h-4 bg-cyan-400 transform rotate-12 inline-block"></span> Register
                </p>
                <h2 className="text-3xl font-bold mb-2">Create your account</h2>
                <p className="text-gray-400 text-sm">Free forever for personal use. No card required.</p>
              </div>

              <form className="space-y-5" onSubmit={handleSubmit} noValidate>

                {/* Nome Completo */}
                <div>
                  <label htmlFor="name" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Full Name</label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    placeholder="Ada Lovelace"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    onBlur={() => setTouched((t) => ({ ...t, name: true }))}
                    className={`${inputBase} ${touched.name && !isNameValid ? inputError : inputOk}`}
                  />
                  {touched.name && !isNameValid && (
                    <p className="text-rose-400 text-xs mt-1.5 flex items-center gap-1">
                      <AlertCircle className="w-3 h-3" /> Full name is required.
                    </p>
                  )}
                </div>

                {/* Email */}
                <div>
                  <label htmlFor="email" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Email</label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="ada@taskmanager.dev"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    onBlur={() => setTouched((t) => ({ ...t, email: true }))}
                    className={`${inputBase} ${touched.email && !isEmailValid ? inputError : inputOk}`}
                  />
                  {touched.email && !isEmailValid && (
                    <p className="text-rose-400 text-xs mt-1.5 flex items-center gap-1">
                      <AlertCircle className="w-3 h-3" /> Enter a valid email address.
                    </p>
                  )}
                </div>

                {/* Senha */}
                <div>
                  <label htmlFor="password" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Password</label>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="••••••••"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    onFocus={() => setPasswordFocused(true)}
                    onBlur={() => {
                      setPasswordFocused(false);
                      setTouched((t) => ({ ...t, password: true }));
                    }}
                    className={`${inputBase} ${touched.password && !isPasswordValid ? inputError : inputOk}`}
                  />

                  {showPasswordPanel && (
                    <div className="mt-3 p-3 bg-[#0a0f1c] border border-gray-800 rounded-lg">
                      <p className="text-[10px] font-bold tracking-wider text-gray-500 uppercase mb-2">Password requirements</p>
                      <ul className="space-y-1.5">
                        <Requirement met={passwordChecks.length} label="At least 8 characters" />
                        <Requirement met={passwordChecks.uppercase} label="One uppercase letter (A-Z)" />
                        <Requirement met={passwordChecks.lowercase} label="One lowercase letter (a-z)" />
                        <Requirement met={passwordChecks.number} label="One number (0-9)" />
                        <Requirement met={passwordChecks.special} label="One special character (!@#$...)" />
                      </ul>
                    </div>
                  )}
                </div>

                {/* Confirmar Senha */}
                <div>
                  <label htmlFor="confirm-password" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Confirm Password</label>
                  <input
                    type="password"
                    id="confirm-password"
                    placeholder="••••••••"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    onBlur={() => setTouched((t) => ({ ...t, confirmPassword: true }))}
                    className={`${inputBase} ${touched.confirmPassword && !passwordsMatch ? inputError : inputOk}`}
                  />
                  {confirmPassword.length > 0 && (
                    passwordsMatch ? (
                      <p className="text-cyan-400 text-xs mt-1.5 flex items-center gap-1">
                        <Check className="w-3 h-3" strokeWidth={3} /> Passwords match.
                      </p>
                    ) : (
                      touched.confirmPassword && (
                        <p className="text-rose-400 text-xs mt-1.5 flex items-center gap-1">
                          <AlertCircle className="w-3 h-3" /> Passwords do not match.
                        </p>
                      )
                    )
                  )}
                </div>

                {/* Checkbox de Termos */}
                <div className="pt-2">
                  <div className="flex items-center gap-2">
                    <input
                      type="checkbox"
                      id="terms"
                      checked={terms}
                      onChange={(e) => {
                        setTerms(e.target.checked);
                        setTouched((t) => ({ ...t, terms: true }));
                      }}
                      className="w-4 h-4 rounded border-gray-700 bg-[#0a0f1c] text-cyan-500 focus:ring-cyan-500 focus:ring-offset-gray-900"
                    />
                    <label htmlFor="terms" className="text-xs text-gray-400">
                      I agree to the <a href="#" className="text-cyan-400 hover:underline">Terms of Service</a> and <a href="#" className="text-cyan-400 hover:underline">Privacy Policy</a>.
                    </label>
                  </div>
                  {touched.terms && !terms && (
                    <p className="text-rose-400 text-xs mt-1.5 flex items-center gap-1">
                      <AlertCircle className="w-3 h-3" /> You must accept the terms to continue.
                    </p>
                  )}
                </div>

                {submitError && (
                  <div className="flex items-center gap-2 p-3 bg-rose-500/10 border border-rose-500/30 rounded-lg">
                    <AlertCircle className="w-4 h-4 text-rose-400 flex-shrink-0" />
                    <p className="text-rose-400 text-xs">{submitError}</p>
                  </div>
                )}

                <button
                  type="submit"
                  disabled={!isFormValid || loading}
                  className="w-full flex items-center justify-center gap-2 py-3 mt-4 bg-cyan-400 text-gray-950 font-bold rounded-lg hover:bg-cyan-300 transition text-sm disabled:bg-gray-700 disabled:text-gray-500 disabled:cursor-not-allowed disabled:hover:bg-gray-700"
                >
                  <CheckCircle2 className="w-4 h-4" />
                  {loading ? "Creating account..." : "Create account"}
                </button>
              </form>

              {/* Card Footer link */}
              <div className="text-center mt-8 pt-6 border-t border-gray-800/50">
                <p className="text-sm text-gray-400">Already have an account? <a href="/auth/login" className="text-cyan-400 font-semibold hover:text-cyan-300 transition">Sign in</a></p>
              </div>
            </div>
          </div>

        </div>
      </div>

      <footer className="w-full max-w-7xl mx-auto px-4 py-6 z-10 text-center text-xs text-gray-600 font-mono flex items-center justify-center">
        <div>© 2026 TaskManager Inc.</div>
      </footer>
    </div>
  )
}
