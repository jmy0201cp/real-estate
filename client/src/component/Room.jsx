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
      className="rounded-lg shadow-md overflow-hidden cursor-pointer max-h-[365px]"
      onClick={() => navigate(`/rooms/${room.roomId}`, { state: room })}
    >
      <img src={room.url} alt="" className="w-full h-80" />
      <div className="mt-2 px-2 text-sm flex justify-between items-center">
        <p className="truncate p-2">{room.address}</p>
        <span>{types[room.contractType]}</span>
      </div>
    </li>
  );
}
