"use client"

import { LogIn, AlertCircle } from 'lucide-react'
import { useState, FormEvent } from 'react'
import { useRouter } from 'next/navigation'
import { loginUser } from '../services/user.api'

const TaskManagerLogo = () => (
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" className="w-10 h-10 drop-shadow-lg" fill="none">
    <defs>
      <linearGradient id="logo-gradient-login-main" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#22d3ee" />
        <stop offset="100%" stopColor="#c084fc" />
      </linearGradient>

      <linearGradient id="logo-gradient-login-dark" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#0891b2" />
        <stop offset="100%" stopColor="#9333ea" />
      </linearGradient>
    </defs>

    <path
      d="M5 16.5 L12 23.5 L27 6.5 L22.5 4.5 L11.5 17.5 L7.5 13.5 Z"
      fill="url(#logo-gradient-login-main)"
    />

    <path
      d="M11 26 L15 30 L29 11.5 L25 8.5 Z"
      fill="url(#logo-gradient-login-dark)"
      opacity="0.8"
    />
  </svg>
)

export function LoginForm() {
  const router = useRouter()
  const [loading, setLoading] = useState(false)
  const [submitError, setSubmitError] = useState<string | null>(null)

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  const [touched, setTouched] = useState({
    email: false,
    password: false,
  })

  const isEmailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
  const isPasswordValid = password.length > 0
  const isFormValid = isEmailValid && isPasswordValid

  const inputBase = "w-full px-4 py-3 bg-[#0a0f1c] border rounded-lg focus:ring-1 transition text-gray-200 text-sm outline-none"
  const inputOk = "border-gray-800 focus:ring-cyan-500 focus:border-cyan-500"
  const inputError = "border-rose-500/60 focus:ring-rose-500 focus:border-rose-500"

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault()
    setTouched({ email: true, password: true })
    setSubmitError(null)

    if (!isFormValid) return

    try {
      setLoading(true)
      const response = await loginUser({ username: email, password })
      localStorage.setItem("token", response.data)
      router.push("/organization")
    } catch (err) {
      console.error(err)
      setSubmitError("Could not sign in. Please check your credentials.")
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen bg-[#0a0f1c] text-white flex flex-col items-center justify-between p-6 relative overflow-hidden">

      <div className="absolute inset-0 z-0 bg-[linear-gradient(to_right,#ffffff0a_1px,transparent_1px),linear-gradient(to_bottom,#ffffff0a_1px,transparent_1px)] bg-[size:32px_32px]"></div>
      <div className="absolute -top-40 -left-40 w-[500px] h-[500px] bg-cyan-600/20 rounded-full blur-[120px] z-0 pointer-events-none"></div>
      <div className="absolute -bottom-40 -right-40 w-[500px] h-[500px] bg-purple-600/20 rounded-full blur-[120px] z-0 pointer-events-none"></div>

      <div className="flex-grow flex items-center justify-center container mx-auto px-4 z-10 w-full py-10">
        <div className="w-full max-w-md bg-[#111625] border border-gray-800 rounded-2xl shadow-2xl p-8 relative overflow-hidden">

          <div className="absolute top-0 left-0 right-0 h-1 bg-gradient-to-r from-transparent via-cyan-500 to-transparent opacity-20"></div>

          <div className="flex items-center gap-3 mb-10 justify-center">
            <TaskManagerLogo />
            <span className="text-2xl font-bold tracking-tight">TaskManager<span className="text-cyan-400">.</span></span>
          </div>

          <div className="mb-8 text-center">
            <p className="text-cyan-400 text-xs font-bold tracking-widest uppercase mb-2 flex items-center justify-center gap-2">
              <span className="w-[2px] h-4 bg-cyan-400 transform rotate-12 inline-block"></span> Sign In
            </p>
            <h2 className="text-3xl font-bold mb-2">Welcome back</h2>
            <p className="text-gray-400 text-sm">Enter your credentials to access your account.</p>
          </div>

          <form className="space-y-5" onSubmit={handleSubmit} noValidate>

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

            {/* Password */}
            <div>
              <label htmlFor="password" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Password</label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                onBlur={() => setTouched((t) => ({ ...t, password: true }))}
                className={`${inputBase} ${touched.password && !isPasswordValid ? inputError : inputOk}`}
              />
              {touched.password && !isPasswordValid && (
                <p className="text-rose-400 text-xs mt-1.5 flex items-center gap-1">
                  <AlertCircle className="w-3 h-3" /> Password is required.
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
              className="w-full flex items-center justify-center gap-2 py-3 mt-4 cursor-pointer bg-cyan-400 text-gray-950 font-bold rounded-lg hover:bg-cyan-300 transition text-sm disabled:bg-gray-700 disabled:text-gray-500 disabled:cursor-not-allowed disabled:hover:bg-gray-700"
            >
              <LogIn className="w-4 h-4" />
              {loading ? "Signing in..." : "Sign in"}
            </button>
          </form>

          <div className="text-center mt-8 pt-6 border-t border-gray-800/50">
            <p className="text-sm text-gray-400">Don't have an account? <a href="/auth/register" className="text-cyan-400 font-semibold hover:text-cyan-300 transition">Create one</a></p>
          </div>
        </div>
      </div>

      <footer className="w-full max-w-7xl mx-auto px-4 py-6 z-10 text-center text-xs text-gray-600 font-mono flex items-center justify-center">
        <div>© 2026 TaskManager Inc.</div>
      </footer>
    </div>
  )
}
