import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import RequestModal from "../component/RequestModal";

const types = {
  JEONSE: "전세",
  MONTHLY: "월세",
  RENTAL: "단기임대",
  PURCHASE: "매매",
};

export default function RoomDetail() {
  const [askFlag, setAskFlag] = useState(false);
  const {
    state: {
      url,
      address,
      contractType,
      price,
      managementFee,
      floor,
      roomCount,
      elevatorYn,
      details,
    },
  } = useLocation();

  const handleAdd = (e) => {
    e.preventDefault();
  };

  const handleModal = () => {
    setAskFlag((prev) => !prev);
  };

  return (
    <div>
      <div>
        <img src={url} alt="room" />
        <button onClick={handleAdd}>찜</button>
      </div>
      <div>
        <ul>
          <li>{address}</li>
          <li>{types[contractType]}</li>
          <li>￦{price}</li>
          <li>{address}</li>
          <li>{managementFee}</li>
          <li>{floor}</li>
          <li>{roomCount}</li>
          <li>{elevatorYn}</li>
          <li>{details}</li>
        </ul>
        <div className="flex">
          <button onClick={handleModal}>문의하기</button>
        </div>
        {!askFlag || <RequestModal />}
      </div>
    </div>
  );
}
