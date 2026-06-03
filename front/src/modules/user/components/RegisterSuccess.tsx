"use client"

import { CheckCircle2, ArrowRight } from 'lucide-react'


const TaskManagerLogo = () => (
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" className="w-10 h-10 drop-shadow-lg" fill="none">
    <defs>
      <linearGradient id="logo-gradient-success-main" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#22d3ee" />
        <stop offset="100%" stopColor="#c084fc" />
      </linearGradient>

      <linearGradient id="logo-gradient-success-dark" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#0891b2" />
        <stop offset="100%" stopColor="#9333ea" />
      </linearGradient>
    </defs>

    <path
      d="M5 16.5 L12 23.5 L27 6.5 L22.5 4.5 L11.5 17.5 L7.5 13.5 Z"
      fill="url(#logo-gradient-success-main)"
    />

    <path
      d="M11 26 L15 30 L29 11.5 L25 8.5 Z"
      fill="url(#logo-gradient-success-dark)"
      opacity="0.8"
    />
  </svg>
)


export function RegisterSuccess() {
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

          <div className="flex justify-center mb-8">
            <div className="relative">
              <div className="absolute inset-0 bg-cyan-400/30 rounded-full blur-2xl"></div>
              <div className="relative bg-gradient-to-br from-cyan-400 to-purple-500 p-5 rounded-full">
                <CheckCircle2 className="w-12 h-12 text-gray-950" strokeWidth={2.5} />
              </div>
            </div>
          </div>

          <div className="text-center mb-8">
            <p className="text-cyan-400 text-xs font-bold tracking-widest uppercase mb-2 flex items-center justify-center gap-2">
              Success
            </p>
            <h2 className="text-3xl font-bold mb-3">Account created!</h2>
            <p className="text-gray-400 text-sm">
              Welcome to TaskManager. Your account is ready — sign in to start shipping work at the speed of thought.
            </p>
          </div>

          <a
            href="/auth/login"
            className="w-full flex items-center justify-center gap-2 py-3 bg-cyan-400 text-gray-950 font-bold rounded-lg hover:bg-cyan-300 transition text-sm"
          >
            Go to sign in
            <ArrowRight className="w-4 h-4" />
          </a>

          <div className="text-center mt-8 pt-6 border-t border-gray-800/50">
            <p className="text-sm text-gray-400">
              Need to create another account?{' '}
              <a href="/auth/register" className="text-cyan-400 font-semibold hover:text-cyan-300 transition">Register again</a>
            </p>
          </div>
        </div>
      </div>

      <footer className="w-full max-w-7xl mx-auto px-4 py-6 z-10 text-center text-xs text-gray-600 font-mono flex items-center justify-center">
        <div>© 2026 TaskManager Inc.</div>
      </footer>
    </div>
  )
}
