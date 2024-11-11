import { Outlet } from "react-router-dom";

export const LandingPage = () => {
  return <div>
    <h1 className="text-5xl font-bold mb-8">Welcome to Our Site</h1>
    <Outlet />
  </div>;
};