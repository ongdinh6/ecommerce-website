import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import { Provider } from "react-redux";
import { store } from "@/app/store.ts";
import { asyncWithLDProvider, basicLogger } from "launchdarkly-react-client-sdk";

(async () => {
  const LDProvider = await asyncWithLDProvider({
    clientSideID: import.meta.env.VITE_LD_CLIENT_SIDE_ID,
    timeout: Number(import.meta.env.VITE_LD_INITIALIZATION_TIMEOUT_SECONDS),
    options: {
      logger: basicLogger({ level: import.meta.env.VITE_LD_LOGGING_LEVEL })
    }
  });

  createRoot(document.getElementById("root")!).render(
    <StrictMode>
      <Provider store={store}>
        <LDProvider>
          <App />
        </LDProvider>
      </Provider>
    </StrictMode>
  );
})();


