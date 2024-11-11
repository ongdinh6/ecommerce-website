import { Link, useLocation } from "react-router-dom";
import { breadCrumbs } from "@/components/Breadcrumbs/types.ts";

export const Breadcrumbs = () => {
  const location = useLocation();
  const pathNames = location.pathname.split("/").filter((x) => x);

  return (
    <nav className="flex" aria-label="breadcrumb">
      <ol className="breadcrumb inline-flex items-center space-x-1 md:space-x-2 rtl:space-x-reverse">
        <li className="inline-flex items-center">
          <Link to="/">Home</Link>
        </li>
        {breadCrumbs.filter(page => page.path.test(location.pathname)).map((value, index) => {
          const to = `/${pathNames.slice(0, index + 1).join("/")}`;
          return (
            <>
            <svg className="rtl:rotate-180 w-3 h-3 text-gray-400 mx-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
              <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m1 9 4-4-4-4"/>
            </svg>
            <li key={to} className="breadcrumb-item">
              <div className="flex items-center">
                <Link to={to}>{value.displayName}</Link>
              </div>
            </li>
          </>
          );
        })}
      </ol>
    </nav>
  );
};

