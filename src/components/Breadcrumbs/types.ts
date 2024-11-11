import { PagePath } from "@/constant.ts";

export interface BreadcrumbData {
  path: RegExp;
  displayName: string;
}

export const breadCrumbs: BreadcrumbData[] = [
  {
    path: new RegExp(`^\\/${PagePath.HOME}`),
    displayName: "Home"
  },
  {
    path: new RegExp(`^\\/${PagePath.LOGIN}`),
    displayName: "Login"
  },
  {
    path: new RegExp(`^\\/${PagePath.USERS}`),
    displayName: "List Users"
  },
  {
    path: new RegExp(`^\\/${PagePath.USERS}\\/.+`),
    displayName: "User Details"
  },
  {
    path: new RegExp(`^\\/${PagePath.CHAT}`),
    displayName: "Chat Beta"
  }
];
