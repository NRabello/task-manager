interface TaskManagerLogoProps {
  className?: string;
}

export function TaskManagerLogo({ className = "w-10 h-10 drop-shadow-lg" }: TaskManagerLogoProps) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 32 32"
      className={className}
      fill="none"
    >
      <defs>
        <linearGradient
          id="logo-gradient-main"
          x1="0%"
          y1="0%"
          x2="100%"
          y2="100%"
        >
          <stop offset="0%" stopColor="#22d3ee" />
          <stop offset="100%" stopColor="#c084fc" />
        </linearGradient>

        <linearGradient
          id="logo-gradient-dark"
          x1="0%"
          y1="0%"
          x2="100%"
          y2="100%"
        >
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
  );
}
