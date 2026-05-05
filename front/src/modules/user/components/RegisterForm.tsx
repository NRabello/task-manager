"use client"

import { CheckCircle2, Bot, ShieldCheck } from 'lucide-react'
import { useState, FormEvent } from 'react';
import { registerUser } from "../services/user.api";


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


export function RegisterForm() {
    const [loading, setLoading] = useState(false);

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);

    const data = {
      name: formData.get("name") as string,
      username: formData.get("email") as string,
      password: formData.get("password") as string,
    };

    try {
      setLoading(true);
      console.log("data: " + JSON.stringify(data));
      const response = await registerUser(data);
      console.log(response);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
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

              <form className="space-y-5" onSubmit={handleSubmit}>
                
                {/* Nome Completo */}
                <div>
                  <label htmlFor="name" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Full Name</label>
                  <input type="text" id="name" name="name" placeholder="Ada Lovelace" className="w-full px-4 py-3 bg-[#0a0f1c] border border-gray-800 rounded-lg focus:ring-1 focus:ring-cyan-500 focus:border-cyan-500 transition text-gray-200 text-sm outline-none" />
                </div>

                {/* Email */}
                <div>
                  <label htmlFor="email" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Email</label>
                  <input type="email" id="email" name="email" placeholder="ada@taskmanager.dev" className="w-full px-4 py-3 bg-[#0a0f1c] border border-gray-800 rounded-lg focus:ring-1 focus:ring-cyan-500 focus:border-cyan-500 transition text-gray-200 text-sm outline-none" />
                </div>

                {/* Senha */}
                <div>
                  <label htmlFor="password" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Password</label>
                  <input type="password" id="password" name="password" placeholder="••••••••" className="w-full px-4 py-3 bg-[#0a0f1c] border border-gray-800 rounded-lg focus:ring-1 focus:ring-cyan-500 focus:border-cyan-500 transition text-gray-200 text-sm outline-none" />
                </div>

                {/* Confirmar Senha */}
                <div>
                  <label htmlFor="confirm-password" className="block text-xs font-bold tracking-wider text-gray-400 uppercase mb-2">Confirm Password</label>
                  <input type="password" id="confirm-password" placeholder="••••••••" className="w-full px-4 py-3 bg-[#0a0f1c] border border-gray-800 rounded-lg focus:ring-1 focus:ring-cyan-500 focus:border-cyan-500 transition text-gray-200 text-sm outline-none" />
                </div>
                
                {/* Checkbox de Termos */}
                <div className="flex items-center gap-2 pt-2">
                  <input type="checkbox" id="terms" className="w-4 h-4 rounded border-gray-700 bg-[#0a0f1c] text-cyan-500 focus:ring-cyan-500 focus:ring-offset-gray-900" />
                  <label htmlFor="terms" className="text-xs text-gray-400">
                    I agree to the <a href="#" className="text-cyan-400 hover:underline">Terms of Service</a> and <a href="#" className="text-cyan-400 hover:underline">Privacy Policy</a>.
                  </label>
                </div>

                <button type="submit" className="w-full flex items-center justify-center gap-2 py-3 mt-4 bg-cyan-400 text-gray-950 font-bold rounded-lg hover:bg-cyan-300 transition text-sm">
                  <CheckCircle2 className="w-4 h-4" />
                  Create account
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