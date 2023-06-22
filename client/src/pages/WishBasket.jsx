import React, { useContext, useEffect, useState } from "react";
import Room from "../component/Room";
import { useNavigate } from "react-router-dom";
import { LoginTokenContext } from "../context/LoginTokenContext";
import httpFetch from "../network/http";

export default function WishBasket() {
  const { token } = useContext(LoginTokenContext);
  const [list, setList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!token) {
      navigate("/");
      return;
    }
    async function fetchData() {
      const data = await httpFetch(`/members/wish`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setList(data);
    }
    fetchData();
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
