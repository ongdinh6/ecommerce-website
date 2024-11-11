import * as path from "path";
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      historyApiFallback: "true",
    },
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src") // Optional: set alias for easier imports
    }
  }
});
