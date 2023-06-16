import React from "react";
import { Link } from "react-router-dom";

export default function Header() {
  return (
    <div>
      <Link to="/" className="m-2">
        HOME
      </Link>
      <Link to="/rooms" className="mr-2">
        ROOMS
      </Link>
    </div>
  );
}
