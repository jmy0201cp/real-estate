import React, { useState } from "react";
import { AiOutlineCompass } from "react-icons/ai";
import { useParams } from "react-router-dom";
import { BsFillHouseHeartFill } from "react-icons/bs";

export default function CommentForm() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [content, setContent] = useState("");
  const { roomId } = useParams();

  const handleChange = (e) => {
    setContent(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (content === null || content === "") {
      alert("내용을 입력해주세요.");
      return;
    }

    if (!window.confirm("댓글을 등록하시겠습니까?")) {
      return;
    }

    await fetch(`/rooms/${roomId}/comment`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ content }),
    });
  };
  return (
    token && (
      <form className="flex items-center" onSubmit={handleSubmit}>
        <BsFillHouseHeartFill className="w-7 h-7 mx-1 mt-1 opacity-75" />
        <div className="bg-[#f5f5f5] mt-2 p-1.5 rounded-md w-full flex justify-between items-center">
          <input
            type="text"
            name="inputComment"
            id="inputComment"
            value={content}
            onChange={handleChange}
            placeholder="please leave comments"
            className="bg-transparent outline-0 px-1 w-full mr-1 text-sm"
          />
          <AiOutlineCompass className="text-base opacity-70" />
        </div>
      </form>
    )
  );
}
