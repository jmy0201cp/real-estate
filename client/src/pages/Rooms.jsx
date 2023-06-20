import React, { useEffect, useState } from "react";
import Room from "../component/Room";
import { useLocation } from "react-router-dom";

export default function RoomContainer() {
  const [rooms, setRooms] = useState([]);
  const { state } = useLocation();

  useEffect(() => {
    fetch(`/rooms?roomType=${state || "all"}`)
      .then((res) => res.json())
      .then((room) => setRooms(room.data));
  }, [state]);

  return (
    <ul className="grid grid-cols-1 md:grid-cols-3 lg-grid-cols-4 gap-4 p-4 h-5/6 overflow-auto">
      {rooms.map((room) => (
        <Room room={room} key={room.roomId} />
      ))}
    </ul>
  );
}
