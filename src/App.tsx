import { BrowserRouter, Route, Routes } from "react-router-dom";
import { LoginPage } from "@/pages/LoginPage";
import { UserDataPage } from "@/pages/UserDataPage";
import { LandingPage } from "@/pages/LandingPage";
import { Breadcrumbs } from "@/components/Breadcrumbs";
import { Header } from "@/components/Header";
import { NotFoundPage } from "@/pages/NotFoundPage";
import { UserDetailPage } from "@/pages/UserDetailPage";
import { ChatPage } from "@/pages/ChatPage";

const App = () => {
  return <BrowserRouter>
    <Header />
    <Breadcrumbs />
    <Routes>
      <Route index path={"/"} element={<LandingPage />} />
      <Route path={"login"} element={<LoginPage />} />
      <Route path={"users"} element={<UserDataPage />} />
      <Route path={"users/:name"} element={<UserDetailPage />} />
      <Route path={"chat"} element={<ChatPage />} />
      <Route path={"*"} element={<NotFoundPage />} />
    </Routes>
  </BrowserRouter>;
};

export default App;
