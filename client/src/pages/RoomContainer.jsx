import React, { useEffect, useState } from "react";
import Room from "../component/Room";

export default function RoomContainer() {
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    fetch(`/data/rooms.json`)
      .then((res) => res.json())
      .then(setRooms);
  }, []);

  return (
    <>
      <ul className="flex flex-wrap p-5">
        {rooms.map((room) => (
          <Room room={room} key={room.id} />
        ))}
      </ul>
    </>
  );
}
