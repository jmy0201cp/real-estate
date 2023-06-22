import React, { useContext, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { LoginTokenContext } from "../context/LoginTokenContext";

const roomTypes = [
  { all: "All" },
  { studio: "Studio" },
  { apartment: "Apartment" },
  { villa: "Villa" },
];

const wishLocation = "/members/wish";

export default function Header() {
  const { token } = useContext(LoginTokenContext);
  const [select, setSelect] = useState("all");
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const handleMoveWish = () => {
    if (!window.confirm("위시리스트 방으로 이동하시겠습니까?")) return;
    navigate(wishLocation);
  };

  return (
    <div className="flex justify-between w-full px-4 py-7 bg-white">
      <div
        style={{
          opacity: `${pathname === wishLocation ? 0 : 1}`,
          pointerEvents: `${pathname === wishLocation ? "none" : "auto"}`,
        }}
      >
        {roomTypes.map((type, index) => {
          const key = Object.keys(roomTypes[index]).join();
          return (
            <span
              className="m-2 text-[22px] cursor-pointer"
              style={{
                textDecoration: `${select === key ? "underline" : "none"} `,
              }}
              key={index}
              onClick={() => {
                navigate("/rooms", { state: key });
                setSelect(key);
              }}
            >
              {type[key]}
            </span>
          );
        })}
      </div>
      <div className="flex mr-3">
        {!token && (
          <span
            className="text-[20px] cursor-pointer hover:opacity-80"
            onClick={() => navigate("/login")}
          >
            Login
          </span>
        )}
        {token && (
          <span
            className="text-[20px] cursor-pointer hover:opacity-80"
            onClick={() => {
              if (!window.confirm("로그아웃하시겠습니까?")) {
                return;
              }
              localStorage.clear();
              window.location.reload();
            }}
          >
            Logout
          </span>
        )}
        {token && (
          <span
            className="text-[20px] ml-3 cursor-pointer hover:opacity-80"
            onClick={handleMoveWish}
          >
            Wish
          </span>
        )}
      </div>
    </div>
  );
}
