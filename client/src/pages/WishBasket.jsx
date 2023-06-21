import React, { useEffect, useState } from "react";
import Room from "../component/Room";
import { useNavigate } from "react-router-dom";

export default function WishBasket() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [list, setList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!token) {
      navigate("/");
      return;
    }

    fetch(`/members/wish`, {
      method: "GET",
      headers: {
        "Context-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((data) => data.json())
      .then((res) => setList(res.data));
  }, []);

  return (
    <>
      <ul className="grid grid-cols-1 md:grid-cols-3 lg-grid-cols-4 gap-4 p-4 h-5/6 overflow-auto">
        {list.map((room) => (
          <Room room={room} key={room.roomId} />
        ))}
      </ul>
    </>
  );
}
