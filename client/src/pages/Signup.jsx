import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import httpFetch from "../network/http";
import DaumPostcode from "react-daum-postcode";

let user = {
  memberName: "",
  password: "",
  phoneNumber: "",
  email: "",
  address: "",
  addressDetail: "",
};

export default function Signup() {
  const [userJoin, setUserJoin] = useState(user);
  const [popOpen, setPopOpen] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const split = userJoin.address.split(" ");
    const address = split.slice(0, 2).join(" ");
    const addressDetail = split.slice(2, split.length + 1).join(" ");

    if (userJoin.memberName === "" || userJoin.password === "") {
      alert("아이디 또는 비밀번호를 입력해주세요");
      return;
    }

    console.log({ ...userJoin, address, addressDetail });
    const response = await httpFetch(`/members/signup`, {
      method: "POST",
      body: JSON.stringify({ ...userJoin, address, addressDetail }),
    });

    if (response) {
      alert("회원가입이 완료됐습니다.");
      navigate("/login");
    }
  };

  const onCompletePost = (e) => {
    const address = e.address;
    setUserJoin({ ...userJoin, address });
  };

  const handleChange = (e) => {
    setUserJoin((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  return (
    <div className="bg-[#e0dfe4] flex flex-col items-center h-screen">
      <img src="/image/logo.png" alt="" className="w-1/3" />
      <div className="w-96">
        <form
          onSubmit={handleSubmit}
          className="flex flex-col z-10 mt-[-180px]"
        >
          <input
            type="text"
            name="memberName"
            id="memberName"
            value={userJoin.memberName}
            onChange={handleChange}
            placeholder="닉네임"
            className="p-2 mb-1.5"
          />
          <input
            type="password"
            name="password"
            id="password"
            value={userJoin.password}
            onChange={handleChange}
            placeholder="비밀번호"
            className="p-2 mb-1.5"
          />
          <input
            type="text"
            name="phoneNumber"
            id="phoneNumber"
            value={userJoin.phoneNumber}
            onChange={handleChange}
            placeholder="핸드폰번호"
            className="p-2 mb-1.5"
          />
          <input
            type="text"
            name="email"
            id="email"
            value={userJoin.email}
            onChange={handleChange}
            placeholder="이메일"
            className="p-2 mb-1.5"
          />
          <input
            type="text"
            name="address"
            id="address"
            value={userJoin.address}
            onClick={() => setPopOpen((prev) => !prev)}
            readOnly
            placeholder="선호 지역"
            className="p-2 mb-1.5 cursor-default"
          />
          {popOpen && (
            <DaumPostcode
              autoClose
              onComplete={onCompletePost}
              className="border border-gray-400 absolute top-[20%]"
              style={{ width: "400px", height: "470px" }}
            />
          )}

          <button className="mt-2 p-1 border bg-gray-400 text-white hover:font-semibold">
            Join
          </button>
        </form>
        <Link to="/login" className="pl-1.5 hover:font-semibold">
          Login
        </Link>
      </div>
    </div>
  );
}
