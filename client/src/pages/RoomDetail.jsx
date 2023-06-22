import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import { BsBookmarkHeartFill, BsBookmarkHeart } from "react-icons/bs";
import CommentContainer from "../component/CommentContainer";
import RegionMap from "../component/RegionMap";

const types = {
  JEONSE: "전세",
  MONTHLY: "월세",
  RENTAL: "단기임대",
  PURCHASE: "매매",
};

export default function RoomDetail() {
  const [heart, setHeart] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token"));
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
    fetch(`/rooms/${roomId}/wishlist`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((data) => data.json())
      .then((res) => setHeart(res.data));
  }, []);

  const handleAdd = async (e) => {
    if (!heart && token == null) {
      alert("로그인 후 가능합니다.");
      return;
    }

    await fetch(`/rooms/${roomId}/wishlist`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
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
