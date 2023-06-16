import React from "react";
import { useNavigate } from "react-router-dom";

export default function Room({ room }) {
  const navigate = useNavigate();

  const types = {
    JEONSE: "전세",
    MONTHLY: "월세",
    RENTAL: "단기임대",
    PURCHASE: "매매",
  };

  return (
    <li
      className="m-1 w-1/3 border border-gray"
      onClick={() => navigate(`/rooms/${room.id}`, { state: room })}
    >
      <img src={room.url} alt="" className="p-2 w-full h-80" />
      <div className="px-2 flex items-center justify-between">
        <p className="p-2">{room.address}</p>
        <span>{types[room.contractType]}</span>
      </div>
    </li>
  );
}
