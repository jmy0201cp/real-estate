import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import ErrorPage from "./pages/ErrorPage";
import RoomDetail from "./pages/RoomDetail";
import Rooms from "./pages/Rooms";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import WishBasket from "./pages/WishBasket";

const root = ReactDOM.createRoot(document.getElementById("root"));

const router = createBrowserRouter([
  {
    path: "/login",
    element: <Login />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/signup",
    element: <Signup />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      { index: true, element: <Rooms /> },
      { path: "/rooms", element: <Rooms /> },
      { path: "/rooms/:roomId", element: <RoomDetail /> },
      { path: "/members/wish", element: <WishBasket /> },
    ],
  },
]);

root.render(
  <>
    <RouterProvider router={router} />
  </>
);
