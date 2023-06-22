import React, { useEffect, useState } from "react";
import Room from "../component/Room";
import { useLocation } from "react-router-dom";
import httpFetch from "../network/http";

export default function RoomContainer() {
  const [rooms, setRooms] = useState([]);
  const { state } = useLocation();

  useEffect(() => {
    async function fetchData() {
      const data = await httpFetch(`/rooms?roomType=${state || "all"}`, {
        method: "GET",
      });
      setRooms(data);
    }
    fetchData();
  }, [state]);

  return (
    <ul className="grid grid-cols-1 md:grid-cols-3 lg-grid-cols-4 gap-4 p-4 h-5/6 overflow-auto">
      {rooms.map((room) => (
        <Room room={room} key={room.roomId} />
      ))}
    </ul>
  );
}
