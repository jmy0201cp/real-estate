import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

let user = {
  memberName: "",
  password: "",
};

export default function Login() {
  const [userLogin, setUserLogin] = useState(user);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    console.log(userLogin);
    e.preventDefault();

    if (userLogin.memberName === "" || userLogin.password === "") {
      alert("아이디 또는 비밀번호를 입력해주세요");
      return;
    }

    const response = await fetch(`/members/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userLogin),
    });

    const result = await response.json();

    if (result.status !== "success") {
      alert(result.status);
      return;
    }

    localStorage.setItem("token", result.data.token);
    navigate("/");

    setUserLogin(user);
  };

  const handleChange = (e) => {
    setUserLogin((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  return (
    <div className="bg-[#e0dfe4] flex flex-col items-center h-screen">
      <img src="/image/logo.png" alt="" className="w-1/3" />
      <div className="w-96">
        <form
          onSubmit={handleSubmit}
          className="flex flex-col z-10 mt-[-150px]"
        >
          <input
            type="text"
            name="memberName"
            id="memberName"
            value={userLogin.memberName}
            onChange={handleChange}
            placeholder="아이디"
            className="p-2"
          />
          <input
            type="password"
            name="password"
            id="password"
            value={userLogin.password}
            onChange={handleChange}
            placeholder="비밀번호"
            className="p-2 mt-1.5"
          />
          <button className="mt-2 p-1 border bg-gray-400 text-white hover:font-semibold">
            Login
          </button>
        </form>
        <div className="flex justify-between py-2 text-xs w-full">
          <Link to="/" className="pl-1.5 hover:font-semibold">
            Just look around without logging in
          </Link>
          <Link to="/signup" className="pr-1.5 hover:font-semibold">
            Join
          </Link>
        </div>
      </div>
    </div>
  );
}
