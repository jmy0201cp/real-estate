import React, { useContext, useState } from "react";
import moment from "moment";
import { LoginTokenContext } from "../context/LoginTokenContext";
import httpFetch from "../network/http";

export default function Comment({ comment }) {
  const { token } = useContext(LoginTokenContext);
  const { commentId, memberName, content, createdAt } = comment;
  const [text, setText] = useState(content);

  const handleChange = (e) => {
    setText(e.target.value);
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    if (!window.confirm("수정하시겠습니까?")) {
      return;
    }
    await httpFetch(`/rooms/comment/${commentId}`, {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ content: text }),
    });
  };

  const handleDelete = async (e) => {
    e.preventDefault();
    if (!window.confirm("삭제하시겠습니까?")) {
      return;
    }
    await httpFetch(`/rooms/comment/${commentId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  };

  return (
    <li className="flex items-center mb-1.5 bg-[#f5f5f5] px-1.5">
      <div className="w-7 h-7 leading-7 rounded-full bg-violet-600 text-center text-white">
        {memberName.toUpperCase().charAt(0)}
      </div>
      <div className="flex flex-col w-full ml-1">
        <div className="">
          <div className="pl-2">
            <span className="text-[12px] mr-2 font-semibold">{memberName}</span>
            <span className="text-[9px]">{converterDate(createdAt)}</span>
          </div>
          <input
            type="text"
            name="inputComment"
            id="inputComment"
            value={text || ""}
            onChange={handleChange}
            readOnly={!token ? true : false}
            className="bg-transparent outline-0 px-1 w-full mr-1 text-sm"
          />
        </div>
        <div className="flex justify-between">
          <span className="text-[9px] p-1">답글달기</span>
          {token && (
            <div>
              <span className="text-[9px] p-1" onClick={handleUpdate}>
                수정
              </span>
              <span className="text-[9px] p-1" onClick={handleDelete}>
                삭제
              </span>
            </div>
          )}
        </div>
      </div>
    </li>
  );
}

function converterDate(date) {
  return moment(date).format("YYYY-MM-DD HH:mm");
}
