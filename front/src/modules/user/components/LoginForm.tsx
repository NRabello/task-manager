import Head from 'next/head';
import Image from 'next/image';

export function LoginForm() {
  return (
    <>
      <Head>
        <title>Entrar | Empresa Exemplo</title>
      </Head>

      <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-4">
        {/* Cartão de Login */}
        <div className="w-full max-w-md bg-white rounded-2xl shadow-xl overflow-hidden">
          
          {/* Cabeçalho */}
          <div className="p-8 pt-10 pb-6 text-center">
            <h1 className="text-3xl font-bold text-slate-900 mb-2">Entrar no Task Manager</h1>
            <p className="text-gray-600 text-lg">Bem-vindo! Por favor, entre para continuar.</p>
          </div>

          {/* Botão Google */}
          <div className="px-8 pb-4">
            <button className="w-full flex items-center justify-center gap-3 py-3 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
              <Image 
                src="/images/google.png" 
                alt="Logo do Google" 
                width={20} 
                height={20} 
              />
              <span className="text-gray-700 font-medium text-lg">Continuar com o Google</span>
            </button>
          </div>

          {/* Divisor */}
          <div className="relative px-8 my-5">
            <div className="absolute inset-0 flex items-center">
              <div className="w-full border-t border-gray-300"></div>
            </div>
            <div className="relative flex justify-center text-sm">
              <span className="px-3 bg-white text-gray-500 uppercase">ou</span>
            </div>
          </div>

          {/* Formulário de E-mail */}
          <form className="px-8 pb-8 space-y-5">
            <div>
              <label htmlFor="email" className="block text-lg font-medium text-slate-900 mb-2">
                Endereço de e-mail
              </label>
              <input
                type="email"
                id="email"
                name="email"
                placeholder="Digite seu endereço de e-mail"
                className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-slate-900 focus:border-slate-900 transition text-lg"
              />
            </div>

            <button type="submit" className="w-full bg-slate-900 text-white py-3 rounded-lg font-semibold text-lg flex items-center justify-center gap-2 hover:bg-slate-800 transition-colors">
              Continuar
              <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                <path fillRule="evenodd" d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z" clipRule="evenodd" />
              </svg>
            </button>
          </form>

          {/* Rodapé do Cartão */}
          <div className="bg-gray-100 px-8 py-5 border-t border-gray-200 text-center">
            <p className="text-gray-700 text-lg">
              Não tem uma conta?{' '}
              <a href="/auth/register" className="font-semibold text-slate-900 hover:underline">
                Inscrever-se
              </a>
            </p>
          </div>
        </div>
      </div>
    </>
  );
}