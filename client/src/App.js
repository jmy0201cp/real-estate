import { Outlet, useNavigate } from "react-router-dom";
import "./App.css";
import Header from "./component/Header";

function App() {
  const navigate = useNavigate();

  return (
    <div className="bg-[#e0dfe4] flex flex-col justify-end items-center h-screen">
      <img
        src="/image/logo.png"
        alt=""
        className="h-72 absolute top-[-57px] cursor-pointer"
        onClick={() => navigate("/")}
      />
      <div className="bg-[#f9f8fd] w-2/3 rounded-t-[30px] h-4/5 overflow-hidden z-10">
        <Header />
        <Outlet />
      </div>
    </div>
  );
}

export default App;
