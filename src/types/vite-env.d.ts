/// <reference types="vite/client" />
import { LDLogLevel } from "launchdarkly-react-client-sdk";

interface ImportMetaEnv {
  readonly VITE_LD_CLIENT_SIDE_ID: string;
  readonly VITE_LD_INITIALIZATION_TIMEOUT_SECONDS: number;
  readonly VITE_LD_LOGGING_LEVEL: LDLogLevel;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}