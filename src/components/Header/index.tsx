import { Link } from "react-router-dom";

export const Header = () => {
  return <nav className="flex space-x-4">
    <Link to="/" className="text-blue-500 hover:underline">Home</Link>
    <Link to="/login" className="text-blue-500 hover:underline">Login</Link>
    <Link to="/users" className="text-blue-500 hover:underline">Users</Link>
    <Link to="/chat" className="text-blue-500 hover:underline">Chat Beta</Link>
    <Link to="/contact" className="text-blue-500 hover:underline">Contact</Link>
  </nav>;
};
