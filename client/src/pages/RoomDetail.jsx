import React, { useContext, useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import { BsBookmarkHeartFill, BsBookmarkHeart } from "react-icons/bs";
import CommentContainer from "../component/CommentContainer";
import RegionMap from "../component/RegionMap";
import { LoginTokenContext } from "../context/LoginTokenContext";
import httpFetch from "../network/http.js";

export default function RoomDetail() {
  const [heart, setHeart] = useState(false);
  const { token } = useContext(LoginTokenContext);
  const { roomId } = useParams();
  const {
    state: {
      url,
      address,
      addressDetail,
      contractType,
      price,
      managementFee,
      floor,
      roomCount,
      elevatorYn,
      details,
    },
  } = useLocation();

  const infoValues = [
    { 주소: `${address} ${addressDetail}` },
    { 계약유형: converterType(contractType) },
    { 가격: `${price.toLocaleString()} 원` },
    { 관리비: `${managementFee.toLocaleString()} 원` },
    { 층수: `${floor} 층` },
    { 방개수: `${roomCount} 개` },
    { 엘레베이터: elevatorYn ? "있음" : "없음" },
    { 상세정보: details },
  ];

  useEffect(() => {
    if (!token) {
      return;
    }
    async function fetchData() {
      const data = await httpFetch(`/rooms/${roomId}/wishlist`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setHeart(data);
    }
    fetchData();
  }, []);

  const handleAdd = async (e) => {
    if (!heart && token == null) {
      alert("로그인 후 가능합니다.");
      return;
    }

    await httpFetch(`/rooms/${roomId}/wishlist`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    setHeart((prev) => !prev);
  };

  return (
    <div className="overflow-auto h-5/6 pb-3">
      <div className="flex p-2">
        <div className="w-full max-w-[550px]">
          <img src={url} alt="room" className="w-full max-h-[500px]" />
          <div className="flex items-end pt-2 pl-2">
            {heart && (
              <BsBookmarkHeartFill
                onClick={handleAdd}
                className="text-[25px] mt-2 cursor-pointer transition-all hover:scale-105"
              />
            )}
            {!heart && (
              <BsBookmarkHeart
                onClick={handleAdd}
                className="text-[25px] mt-2 cursor-pointer transition-all hover:scale-105"
              />
            )}
            <span className="text-xs font-semibold ml-1.5">Save Wish-list</span>
          </div>
        </div>
        {/* 상세정보 */}
        <div className="px-3 py-1 flex w-full">
          <ul className="p-[10px] pt-0 font-black text-[12px] w-full">
            {infoValues.map((info, index) => {
              const key = Object.keys(infoValues[index]).join();
              return (
                <li className="p-2 flex border-b " key={index}>
                  <span className="w-[100px] max-w-[100px] pl-2">{key}</span>
                  <p className="text-center font-thin w-full pr-3">
                    {info[key]}
                  </p>
                </li>
              );
            })}
          </ul>
        </div>
      </div>
      <div className="flex">
        <div className="border border-black w-3/4 m-2 mr-3">
          네이버 지도 자리
        </div>
        {/* <RegionMap address={addressDetail} /> */}
        <CommentContainer />
      </div>
    </div>
  );
}

function converterType(type) {
  switch (type) {
    case "MONTHLY":
      return "월세";
    case "JEONSE":
      return "전세";
    case "SHORT":
      return "단기임대";
    default:
      return "매매";
  }
}
