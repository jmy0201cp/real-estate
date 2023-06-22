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
        <div>
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
        <div className="px-3 py-1">
          <div className="flex">
            <ul className="p-[10px] font-black">
              <li className="pb-3">주소</li>
              <li className="pb-3">계약유형</li>
              <li className="pb-3">가격</li>
              <li className="pb-3">주소</li>
              <li className="pb-3">관리비</li>
              <li className="pb-3">층수</li>
              <li className="pb-3">방개수</li>
              <li className="pb-3">엘레베이터</li>
              <li>상세정보</li>
            </ul>
            <ul className="text-center p-[10px]">
              <li className="pb-3">{address}</li>
              <li className="pb-3">{types[contractType]}</li>
              <li className="pb-3">￦{price}</li>
              <li className="pb-3">
                {address} {addressDetail}
              </li>
              <li className="pb-3">{managementFee}</li>
              <li className="pb-3">{floor}</li>
              <li className="pb-3">{roomCount}</li>
              <li className="pb-3">{elevatorYn}</li>
              <li>{details}</li>
            </ul>
          </div>
        </div>
      </div>
      <div className="flex">
        <RegionMap address={addressDetail} />
        <CommentContainer />
      </div>
    </div>
  );
}
