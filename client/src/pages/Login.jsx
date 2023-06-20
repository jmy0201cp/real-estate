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
    <>
      <h1>로그인 페이지</h1>
      <form onSubmit={handleSubmit} className="flex flex-col w-96">
        <input
          type="text"
          name="memberName"
          id="memberName"
          value={userLogin.memberName}
          onChange={handleChange}
          placeholder="아이디"
          className="border border-black"
        />
        <input
          type="password"
          name="password"
          id="password"
          value={userLogin.password}
          onChange={handleChange}
          placeholder="비밀번호"
          className="border border-black"
        />
        <button>로그인</button>
      </form>
      <Link to="/">로그인하지 않고 둘러보기</Link>
      <Link to="/signup">회원가입</Link>
    </>
  );
}
