import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import ErrorPage from "./pages/ErrorPage";
import Main from "./pages/Main";
import RoomDetail from "./pages/RoomDetail";
import RoomContainer from "./pages/RoomContainer";
import Login from "./pages/Login";

const root = ReactDOM.createRoot(document.getElementById("root"));

const router = createBrowserRouter([
  {
    path: "/login",
    element: <Login />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      { index: true, element: <Main /> },
      { path: "/rooms", element: <RoomContainer /> },
      { path: "/rooms/:roomId", element: <RoomDetail /> },
    ],
  },
]);

root.render(
  <>
    <RouterProvider router={router} />
  </>
);
